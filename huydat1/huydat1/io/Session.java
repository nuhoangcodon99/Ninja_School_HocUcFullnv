package huydat.io;

import huydat.real.Player;
import huydat.server.HandleController;
import huydat.server.Ip;
import huydat.server.Manager;
import huydat.server.Server;
import huydat.stream.Client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Session {
    private static int baseId = 0;
    private static String KEY = "Rc203_";

    public static boolean check(String ip4string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean check1(String ip4string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    public int id;
    public long outdelay = 20L;
    public boolean connected = false;
    private boolean getKeyComplete = false;
    private byte curR;
    private byte curW;
    protected Socket socket;
    protected DataInputStream dis;
    protected DataOutputStream dos;
    ISessionHandler messageHandler;
    private Object LOCK = new Object();
    public Player player = null;
    private byte type;
    public byte zoomLevel;
    private boolean isGPS;
    private int width;
    private int height;
    private boolean isQwert;
    private boolean isTouch;
    private String plastfrom;
    private byte languageId;
    private int provider;
    private String agent;
    public int version;
    private Server server = Server.gI();
    public String ipv4 = null;
    public boolean login;
    public int idSer;
    private Session.Sender sender;
    private Thread collectorThread;
    protected Thread sendThread;
    public InetSocketAddress socketAddress;
    public String clientIpAddress;
    public volatile long lastTimeReceiveData;
    private String versionARM;

    public Session(Socket socket, ISessionHandler handler) {
        try {
            this.id = baseId++;
            this.socket = socket;
            this.dis = new DataInputStream(this.socket.getInputStream());
            this.dos = new DataOutputStream(this.socket.getOutputStream());
            this.connected = true;
            this.messageHandler = handler;
            this.sendThread = new Thread(this.sender = new Session.Sender());
            this.collectorThread = new Thread(new Session.MessageCollector());
            this.socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
            this.clientIpAddress = socketAddress.getAddress().getHostAddress();
        } catch (Exception e) {

        }
    }

    public void run() {
        this.sendThread.start();
        this.collectorThread.start();
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void sendMessage(Message message) {
        if(this.isConnected()) {
            synchronized (this.LOCK) {
                this.sender.addMessage(message);
                this.LOCK.notify();
            }
        }
    }

    protected synchronized void doSendMessage(Message m) {
        try {
            byte[] data = m.getData();
            if (data != null) {
                byte b = m.getCommand();
                int size = data.length;
                if (size > 65535) {
                    b = -32;
                }

                if (this.getKeyComplete) {
                    this.dos.writeByte(this.writeKey(b));
                } else {
                    this.dos.writeByte(b);
                }

                byte byte1;
                byte byte2;
                if (b == -32) {
                    b = m.getCommand();
                    if (this.getKeyComplete) {
                        this.dos.writeByte(this.writeKey(b));
                    } else {
                        this.dos.writeByte(b);
                    }

                    byte1 = this.writeKey((byte)(size >> 24));
                    this.dos.writeByte(byte1);
                    byte2 = this.writeKey((byte)(size >> 16));
                    this.dos.writeByte(byte2);
                    int byte3 = this.writeKey((byte)(size >> 8));
                    this.dos.writeByte(byte3);
                    int byte4 = this.writeKey((byte)(size & 255));
                    this.dos.writeByte(byte4);
                } else if (this.getKeyComplete) {
                    byte1 = this.writeKey((byte)(size >> 8));
                    this.dos.writeByte(byte1);
                    byte2 = this.writeKey((byte)(size & 255));
                    this.dos.writeByte(byte2);
                } else {
                    byte1 = (byte)(size & '\uff00');
                    this.dos.writeByte(byte1);
                    byte2 = (byte)(size & 255);
                    this.dos.writeByte(byte2);
                }
                if (this.getKeyComplete) {
                    for(int i = 0; i < size; ++i) {
                        data[i] = this.writeKey(data[i]);
                    }
                }
                this.dos.write(data);
            }
            this.dos.flush();
        } catch (Exception var9) {
            System.out.println("Error write message from client " + this.id);
            this.closeMessage();
        } finally {
            if(m != null) {
                m.cleanup();
            }
        }
    }

    private byte readKey(byte b) {
        byte[] bytes = KEY.getBytes();
        byte curR = this.curR;
        this.curR = (byte)(curR + 1);
        byte i = (byte)(bytes[curR] & 255 ^ b & 255);
        if (this.curR >= KEY.getBytes().length) {
            this.curR %= (byte)KEY.getBytes().length;
        }
        return i;
    }

    private byte writeKey(byte b) {
        byte[] bytes = KEY.getBytes();
        byte curW = this.curW;
        this.curW = (byte)(curW + 1);
        byte i = (byte)(bytes[curW] & 255 ^ b & 255);
        if (this.curW >= KEY.getBytes().length) {
            this.curW %= (byte)KEY.getBytes().length;
        }
        return i;
    }

    public void hansakeMessage() {
        Message m = null;
        try {
            m = new Message(-27);
            m.writer().writeByte(KEY.getBytes().length);
            m.writer().writeByte(KEY.getBytes()[0]);
            for(int i = 1; i < KEY.getBytes().length; ++i) {
                m.writer().writeByte(KEY.getBytes()[i] ^ KEY.getBytes()[i - 1]);
            }
            m.writer().flush();
            this.doSendMessage(m);
            this.getKeyComplete = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void setConnect(Message m) {
        try {
            this.type = m.reader().readByte();
            this.zoomLevel = m.reader().readByte();
            this.isGPS = m.reader().readBoolean();
            this.width = m.reader().readInt();
            this.height = m.reader().readInt();
            this.isQwert = m.reader().readBoolean();
            this.isTouch = m.reader().readBoolean();
            this.plastfrom = m.reader().readUTF();
            m.reader().readInt();
            m.reader().readByte();
            this.languageId = m.reader().readByte();
            this.provider = m.reader().readInt();
            this.agent = m.reader().readUTF();
            m.cleanup();
            Util.Debug("Connection type " + this.type + " zoomlevel " + this.zoomLevel + " width " + this.width + " height " + this.height);
        } catch (Exception var3) {
            this.closeMessage();
        } finally {
            if(m != null) {
                m.cleanup();
            }
        }
    }

    public void loginGame(Message m) {
        try {
            if (this.login) {
                return;
            }
            String uname = Util.strSQL(m.reader().readUTF());
            String passw = Util.strSQL(m.reader().readUTF());
            String version = m.reader().readUTF();
            this.version = Integer.parseInt(version.replace(".", ""));
            String t1 = m.reader().readUTF();
            String packages = m.reader().readUTF();
            String random = m.reader().readUTF();
            byte sv = m.reader().readByte();
            Player p = Player.login(this, uname, passw);
            if (p != null && (Util.CheckString(passw, "^[a-zA-Z0-9]+$"))) {
                this.player = p;
                this.outdelay = 0L;
                this.login = true;
                     this.versionARM = version;
                Manager.getPackMessage(p);
                HandleController.selectNinja(p, null);
                         System.out.println("Đăng nhập - Session id: " + this.id + " - tài khoản: " + p.username+ " - mật khẩu: " + p.password + " - name : " + p.ninja + " - ip: " + this.clientIpAddress + " - Số Người Online : " + Client.gI().conns_size() + " - phien ban : " + this.versionARM );
               p.lslog(p.username,p.password,p.ninja , this.ipv4);
               server.manager.sendData(p);
                server.manager.sendItem(p);
                server.manager.sendSkill(p);
                server.manager.sendMap(p);
            } else {
                this.login = false;
                if (!(Util.CheckString(passw, "^[a-zA-Z0-9]+$"))) {
                    this.closeMessage();
                }
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            this.closeMessage();
        } finally {
            if(m != null) {
                m.cleanup();
            }
        }
    }
 public static boolean WriteIp(String ip){
        boolean b = false;
        List<Ip> list = Util.ReadIp();
        for (Ip o : list) {
            if(o.getName().equals(ip)){
                b = true;
                break;
            }else{
                b = false;
            }
        }
        return b;
    }
    public void disconnect() {
           if (!this.connected) {
            for (int jj = 0; jj < Server.arrayListIP.size(); jj++) {
                if (Server.arrayListIP.get(jj).equals(this.ipv4)) {
                    //Server.arrayListIP.remove(jj);
                    break;
                }
            }
            return;
        }
        if (this.connected) {
            if (this.messageHandler != null) {
                this.messageHandler.onDisconnected(this);
            }
            if (this.id >= 40) {
              try {
           String str = Util.toDateString(Date.from(Instant.now())); // Lấy thời gian hiện tại dưới dạng chuỗi
                 String filename = "lichsu/lsip.txt";
                   FileWriter fw = new FileWriter(filename, true);
                     BufferedWriter bw = new BufferedWriter(fw);
            String fileContent = readFile(filename); // Đọc nội dung tệp và lưu trữ vào biến fileContent
            List<String> ipList = getIPList(fileContent); // Lấy danh sách địa chỉ IP đã xuất hiện trong tệp

           
            if (!ipList.contains(this.ipv4)&& !this.ipv4.equals("127.0.0.1") && !this.ipv4.equals("113.23.42.45")) { // Kiểm tra xem địa chỉ IP của đối tượng hiện tại đã xuất hiện trong tệp hay chưa
               
              
              
                bw.write(this.ipv4 + "\n"); // Ghi địa chỉ IP của đối tượng hiện tại vào tệp
                bw.close();
                fw.close();

                ipList.add(this.ipv4); // Thêm địa chỉ IP của đối tượng hiện tại vào danh sách đã lưu trữ
            }
        } catch (Exception e) {
            System.out.println(e);
        }
            }
             if (this.id >= 40) {
             try {
           String str = Util.toDateString(Date.from(Instant.now())); // Lấy thời gian hiện tại dưới dạng chuỗi
                 String filename = "lichsu/lichsuip.txt";
                   FileWriter fw = new FileWriter(filename, true);
                     BufferedWriter bw = new BufferedWriter(fw);
            String fileContent = readFile(filename); // Đọc nội dung tệp và lưu trữ vào biến fileContent
            List<String> ipList = getIPList(fileContent); // Lấy danh sách địa chỉ IP đã xuất hiện trong tệp

           
            if (!ipList.contains(this.ipv4)&& !this.ipv4.equals("127.0.0.1")&& !this.ipv4.equals("113.23.42.45")) { // Kiểm tra xem địa chỉ IP của đối tượng hiện tại đã xuất hiện trong tệp hay chưa
               
              
              
                bw.write(this.ipv4 + "\n"); // Ghi địa chỉ IP của đối tượng hiện tại vào tệp
                bw.close();
                fw.close();

                ipList.add(this.ipv4); // Thêm địa chỉ IP của đối tượng hiện tại vào danh sách đã lưu trữ
            }
        } catch (Exception e) {
            System.out.println(e);
        }
            }
            this.cleanNetwork();
            System.err.println("sension: " + this.id + " Ngắt kết nối - IP "+ this.ipv4 );
             System.err.println("Số Người Online : " + + Client.gI().conns_size());
            synchronized(this.LOCK) {
                this.LOCK.notify();
            }
        }

    }
private static String getIPAddress() throws IOException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        return inetAddress.getHostAddress();
    }

    private static String readFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
        }
        br.close();
        return sb.toString();
    }
    private static List<String> getIPList(String fileContent) {
        List<String> ipList = new ArrayList<>();
        String[] lines = fileContent.split("\\r?\\n");
        for (String line : lines) {
            String ip = line.trim();
            if (!ip.isEmpty() && !ipList.contains(ip)) {
                ipList.add(ip);
            }
        }
        return ipList;
    }
    public void closeMessage() {
        if (this.connected) {
            try {
                Client.gI().removeClient(this);
                Client.gI().kickSession(this);
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }
    }

    private void cleanNetwork() {
        this.curR = 0;
        this.curW = 0;
        try {
            this.connected = false;
            this.login = false;
            if (this.dos != null) {
                this.dos.close();
                this.dos = null;
            }
            if (this.dis != null) {
                this.dis.close();
                this.dis = null;
            }
            if (this.socket != null) {
                this.socket.close();
                this.socket = null;
            }
            this.sendThread = null;
            this.collectorThread = null;
            this.sender = null;
            this.messageHandler = null;
            this.server = null;
            this.player = null;
            synchronized(this.LOCK) {
                this.LOCK.notify();
            }
            System.gc();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public String toString() {
        return "Conn:" + this.id;
    }

    public void sendMessageLog(String str) {
        Message m = null;
        try {
            m = new Message(-26);
            m.writer().writeUTF(str);
            m.writer().flush();
            this.sendMessage(m);
        } catch (Exception var3) {
            var3.printStackTrace();
        } finally {
            if(m != null) {
                m.cleanup();
            }
        }

    }

    class MessageCollector implements Runnable {
        public void run() {
            Message message = null;
            while(true) {
                try {
                    if (Session.this.connected && Session.this.dis != null) {
                        message = this.readMessage();
                        if (message != null && Session.this != null) {
                           if(!login && !(message.getCommand() == -27 || message.getCommand() == -29)){
                     if(!WriteIp(Session.this.clientIpAddress)){
                        Util.WriteIp(Session.this.clientIpAddress);
                        Session.this.socket.close();
                    }
                }else{
                
                            Util.Debug("Session: " + Session.this.id + " do message " + message.getCommand() + " size " + message.reader().available());
                            Session.this.messageHandler.processMessage(Session.this, message);
                            message.cleanup();
                            continue;
                        }
                    }
                    }
                } catch (Exception var2) {
                    var2.printStackTrace();
                } finally {
                    if(message != null) {
                        message.cleanup();
                    }
                }
                Session.this.closeMessage();
                Session.this.dis = null;
                return;
            }
        }

        private Message readMessage() {
            try {
                byte cmd = Session.this.dis.readByte();

                if (cmd != -27) {
                    cmd = Session.this.readKey(cmd);
                }
                int size;
                if (cmd != -27) {
                    byte b1 = Session.this.dis.readByte();
                    byte b2 = Session.this.dis.readByte();
                    size = (Session.this.readKey(b1) & 255) << 8 | Session.this.readKey(b2) & 255;
                } else {
                    size = Session.this.dis.readUnsignedShort();
                }
                byte[] data = new byte[size];
                int len = 0;
                int byteRead = 0;
                while(len != -1 && byteRead < size) {
                    len = Session.this.dis.read(data, byteRead, size - byteRead);
                    if (len > 0) {
                        byteRead += len;
                    }
                }

                if (cmd != -27) {
                    int i;
                    for(i = 0; i < data.length; ++i) {
                        data[i] = Session.this.readKey(data[i]);
                    }
                }
                Message msg = new Message(cmd, data);

                return msg;
            } catch (Exception var7) {
                return null;
            }
        }
    }

    private class Sender implements Runnable {
        private ArrayList<Message> sendingMessage;

        private Sender() {
            this.sendingMessage = new ArrayList();
        }

        public void addMessage(Message message) {
            if (Session.this.isConnected()) {
                this.sendingMessage.add(message);
            }
        }

        public void run() {
            while(true) {
                try {
                    if (Session.this.connected && Session.this.dis != null) {
                        while(this.sendingMessage != null && this.sendingMessage.size() > 0 && Session.this.dos != null) {
                            Message m = this.sendingMessage.remove(0);
                            if (m != null) {
                                Session.this.doSendMessage(m);
                                m.cleanup();
                            }
                        }
                        Thread.sleep(10L);
                        continue;
                    }
                } catch (Exception var2) {
                    var2.printStackTrace();
                }
                try {
                    this.sendingMessage.removeAll(this.sendingMessage);
                    this.sendingMessage = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }
}