/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huydat.server;

import com.sun.management.OperatingSystemMXBean;
import huydat.io.SQLManager;
import huydat.io.Session;
import huydat.io.Util;
import huydat.real.Admission;
import huydat.real.Body;
import huydat.real.Char;
import huydat.real.Item;
import huydat.real.Option;
import huydat.real.Player;
import huydat.stream.Admin;
import huydat.stream.Client;
import huydat.stream.SaveData;
import huydat.template.ItemTemplate;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 *
 * @ Vũ Huy Đạt
 */
  
public class PanelHuyDat extends JPanel {
    private JButton maintenanceButton;
    private JButton maintenanceButton1;
    private JButton maintenanceButton2;
    private JButton maintenanceButton3;
    private JButton maintenanceButton4;
    private JButton maintenanceButton5;
    private JButton maintenanceButton6;
    private JButton maintenanceButton7;
    private JButton maintenanceButton8;
    private JButton maintenanceButton9;
    private JButton maintenanceButton10;
    private JButton maintenanceButton11;
    private JButton maintenanceButton12;
    private JButton maintenanceButton13;
    private JButton maintenanceButton14;
    private JButton maintenanceButton15;
    private JButton maintenanceButton16;
    private JButton maintenanceButton17;
    private JButton maintenanceButton18;
    private JButton maintenanceButton19;
    private JButton maintenanceButton20;
    private JButton maintenanceButton21;
    private JButton maintenanceButton22;
    private JButton maintenanceButton23;
    public ArrayList<Player> players = new ArrayList();

    void initializeComponents() {
        maintenanceButton = new JButton("Bảo Trì");
        maintenanceButton1 = new JButton("Cập Nhật BXH ");
        maintenanceButton3 = new JButton("THÔNG BÁO");
        maintenanceButton2 = new JButton("Lưu Data");
        maintenanceButton4 = new JButton("Clear Clone Login");
        maintenanceButton5 = new JButton("Clear Sesion");
        maintenanceButton6 = new JButton("Thay đổi exp");
        maintenanceButton7 = new JButton("Ðăng Thông Báo");
        maintenanceButton8 = new JButton("Ban Người Chơi");
        maintenanceButton15 = new JButton("Mở Ban Người Chơi");
        maintenanceButton9 = new JButton("Active Người Chơi");
        maintenanceButton11 = new JButton("Send Lượng");
        maintenanceButton10 = new JButton("Send Xu");
        maintenanceButton12 = new JButton("Send Yên");
        maintenanceButton13 = new JButton("Send Trang Bị");
        maintenanceButton14 = new JButton("Send Đồ");
        maintenanceButton16 = new JButton("Thay Đổi Giới Hạn NICK");
        maintenanceButton17 = new JButton("Thay đổi EVEN");
        maintenanceButton15 = new JButton("Thông Tin Sever");
        maintenanceButton18 = new JButton("Thay Thông Báo");
        maintenanceButton19 = new JButton("Set Hiệu Ứng");
        maintenanceButton20 = new JButton("Thêm Npc");
        maintenanceButton21 = new JButton("Thêm Cây");
        maintenanceButton22 = new JButton("Thêm Item Shop");
        maintenanceButton23 = new JButton("Cập Nhập Data");
        //Khởi tạo các nút
        
        //Bộ xử lý
        maintenanceButton.addActionListener(e -> {
            //Xử lý bảo trì
            int minutes = 0;
            Thread t1 = new Thread(new Admin(minutes, Server.gI()));
            t1.start();
            JOptionPane.showMessageDialog(null, "Đã bảo trì");
        });
        // Khi button 1 được click
        maintenanceButton1.addActionListener(e -> {
            // Xử lý save data trước khi bảo trì 
            Service.AutoSaveData();
            JOptionPane.showMessageDialog(null, "UPDATE BXH THÀNH CÔNG");
        });
        // Khi button 2 được click
        maintenanceButton2.addActionListener(e -> {
            SaveData saveData = new SaveData();
            Thread t1 = new Thread(saveData);
            t1.start();
            if(!Manager.isSaveData) {
                t1 = null;
                saveData= null;
            }
            JOptionPane.showMessageDialog(null, "SAVE DATA THÀNH CÔNG");
        });
        // Khi button 3 được click
        maintenanceButton3.addActionListener(e -> {
            try {
                ResultSet resultSet = SQLManager.stat.executeQuery("SELECT * FROM `alert` WHERE `id` = 1;");
                if (resultSet != null && resultSet.first()) {
                    String alert = resultSet.getString("content");
                    Manager.alert.setAlert(alert);
                    resultSet.close();
                    JOptionPane.showMessageDialog(null, "Cập nhật thông báo thành công");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi cập nhật: " + ex.getMessage());
            }
        });
        // Khi button 4 được click
        maintenanceButton4.addActionListener(e -> {
            synchronized (Client.gI().conns) {
                for (int i = 0; i < Client.gI().conns.size(); ++i) {
                    Session conn = (Session) Client.gI().conns.get(i);
                    if (conn != null) {
                        Player player = conn.player;
                        if (player != null) {
                            if (player.c == null) {
                                Client.gI().kickSession(conn);
                            }
                        } else if (player == null) {
                            Client.gI().kickSession(conn);
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Clear Clone Login Thành Công");
        });
        // Khi button 5 được click
        maintenanceButton5.addActionListener(e -> {
            synchronized (Client.gI().conns) {
                for (int i = 0; i < Client.gI().conns.size(); ++i) {
                    Player player = ((Session) Client.gI().conns.get(i)).player;
                    if (player != null && player != player) {
                        Client.gI().kickSession(player.conn);
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Clear Session Thành Công");
        });
        JTextField textField = new JTextField();
        // Khi button 6 được click
        maintenanceButton6.setText("Thay đổi EXP"); // Đặt tên
        maintenanceButton6.addActionListener(e -> {
            String str = JOptionPane.showInputDialog(null, "Nhập giá trị thay đổi:", "Thay đổi EXP", JOptionPane.INFORMATION_MESSAGE);
            if (str == null || str.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Giá trị thay đổi không hợp lệ");
                return;
            }
            String check = str.replaceAll("\\s+", "").trim();
            try {
                int expup = Integer.parseInt(check);
                if (expup <= 0) {
                    expup = 1;
                }
                Manager.up_exp = expup;
                JOptionPane.showMessageDialog(null, "Thay đổi thành công - Giá trị thay đổi "+ expup);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Giá trị nhập vào không hợp lệ");
            }
        });
        // Khi button 7 được click
        maintenanceButton7.setText("Ðang thông báo"); // 
        maintenanceButton7.addActionListener(e -> {
            String str = JOptionPane.showInputDialog(null, "Nhập thông báo:", "Ðang thông báo", JOptionPane.INFORMATION_MESSAGE);

            if (str == null || str.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Giá trị nhập vào không hợp lệ");
                return;
            }

            String check = str.replaceAll("\\s+", "").trim();

            Manager.serverChat("Server", str);
//            JOptionPane.showMessageDialog(null, "Ðang thông báo thành công");
            JOptionPane.showMessageDialog(null, "Đăng Thông Báo Thành Công => " + str );

        });
        // Khi button 8 được click
        maintenanceButton8.addActionListener(e -> {
            // Hiển thị input dialog để nhập tên nhân vật
            String str = JOptionPane.showInputDialog(null, "Nhập Tên Nhân Vật", "Nhập Tên Nhân Vật Người Chơi", JOptionPane.INFORMATION_MESSAGE);
            // Lấy thông tin của nhân vật dựa trên tên vừa nhập
            Char temp = Client.gI().getNinja(str);
            if (temp != null) {
                // Nếu tìm thấy nhân vật thì kiểm tra nó có phải là admin hay không và đá khỏi phiên chơi nếu không phải
                Player banPlayer = Client.gI().getPlayer(temp.p.username);
                if (banPlayer != null && banPlayer.role != 9999) {
                    Client.gI().kickSession(banPlayer.conn);
                    try {
                        SQLManager.stat.executeUpdate("UPDATE `player` SET `ban`=1 WHERE `id`=" + banPlayer.id + " LIMIT 1;");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null,"Đã khóa tài khoản: " + banPlayer.username + " - Nhân vật: " + temp.name);
                } else {
                    JOptionPane.showMessageDialog(null, "Nhân Vật này là ADMIN hoặc không tìm thấy tài khoản này!");
                }
            } else {
                JOptionPane.showMessageDialog(null,"Người chơi này không tồn tại hoặc không đang online!");
            }
        });
        // Khi button 9 được click
        maintenanceButton9.addActionListener(e -> {
            // Hiển thị input dialog để nhập tên nhân vật
            String str = JOptionPane.showInputDialog(null, "Nhập Tên Nhân Vật", "Nhập Tên Nhân Vật Người Chơi", JOptionPane.INFORMATION_MESSAGE);
            try {
                // Lấy thông tin của người chơi dựa trên tên vừa nhập
                String selectQuery = "SELECT * FROM `player` WHERE `username`='" + str + "' LIMIT 1;";
                ResultSet resultSet = SQLManager.stat.executeQuery(selectQuery);

                if (resultSet.next()) {
                    // Nếu tìm thấy người chơi thì kích hoạt tài khoản
                    String updateQuery = "UPDATE `player` SET `lock`=0 WHERE `username`='" + str + "' LIMIT 1;";
                    SQLManager.stat.executeUpdate(updateQuery);
                    JOptionPane.showMessageDialog(null, "Đã kích hoạt tài khoản của người chơi: " + str);
                } else {
                    // Nếu không tìm thấy người chơi thì hiển thị thông báo lỗi
                    JOptionPane.showMessageDialog(null, "Không có tài khoản: " + str, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        // Khi button 10 được click
        maintenanceButton10.addActionListener(e -> {
            try {
                // Tạo các thành phần JTextField
                JTextField nameField = new JTextField();
                JTextField xuField = new JTextField();

                // Tạo JPanel để chứa các JTextFields theo định dạng lưới
                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Tên Nhân Vật:"));
                panel.add(nameField);
                panel.add(new JLabel("Số Xu Cần Tặng:"));
                panel.add(xuField);

                // Hiển thị hộp thoại JOptionPane và lấy giá trị đầu vào
                int option = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String nameUS = nameField.getText();
                    int xuGFF = Integer.parseInt(xuField.getText());

                    // Thực hiện xử lý tương ứng khi người dùng nhấn OK
                    Char temp = Client.gI().getNinja(nameUS);
                    if (temp != null) {
                        String check = temp.name.replaceAll(" ", "").trim();
                        temp.upxuMessage(xuGFF);
                        JOptionPane.showMessageDialog(null, "Đã cộng " + xuGFF + " xu cho người chơi " + nameUS, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy nhân vật " + nameUS, "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Khi button 11 được click
        maintenanceButton11.addActionListener(e -> {
            try {
                // Tạo các thành phần JTextField
                JTextField nameField = new JTextField();
                JTextField xuField = new JTextField();

                // Tạo JPanel để chứa các JTextFields theo định dạng lưới
                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Tên Nhân Vật:"));
                panel.add(nameField);
                panel.add(new JLabel("Số Lượng Cần Tăng:"));
                panel.add(xuField);

                // Hiển thị hộp thoại JOptionPane và lấy giá trị đầu vào
                int option = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String nameUS = nameField.getText();
                    int luongGFF = Integer.parseInt(xuField.getText());

                    // Thực hiện xử lý tương ứng khi người dùng nhấn OK
                    Char temp = Client.gI().getNinja(nameUS);
                    if (temp != null) {
                        String check = temp.name.replaceAll(" ", "").trim();
                        temp.p.upluongMessage(luongGFF);
                        JOptionPane.showMessageDialog(null, "Đã cộng " + luongGFF + " lượng cho người chơi " + nameUS, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy nhân vật " + nameUS, "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Khi button 12 được click
        maintenanceButton12.addActionListener(e -> {
            try {
                JTextField nameField = new JTextField();
                JTextField yenField = new JTextField();

                Object[] message = {
                        "Tên Nhân Vật:", nameField,
                        "Số Yên Cần Tăng:", yenField
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Nhập thông tin", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String nameUS = nameField.getText();
                    int yenGFF = Integer.parseInt(yenField.getText());
                    Char temp = Client.gI().getNinja(nameUS);
                    if (temp != null) {
                        String check = temp.name.replaceAll(" ", "").trim();
                        temp.upyenMessage(yenGFF);
                        JOptionPane.showMessageDialog(null, "Đã cộng " + yenGFF + " yên cho người chơi " + nameUS, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy nhân vật " + nameUS, "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Khi button 13 được click
        maintenanceButton13.addActionListener(e -> {
            try {
                JPanel panel = new JPanel(new GridLayout(0, 2));

                JTextField nameTextField = new JTextField();
                JTextField idItemTextField = new JTextField();
                JTextField itemQuantityTextField = new JTextField();
                JTextField itemUpgradeTextField = new JTextField();
                JTextField itemSysTextField = new JTextField();

                panel.add(new JLabel("Nhập tên nhân vật:"));
                panel.add(nameTextField);
                panel.add(new JLabel("Nhập ID vật phẩm:"));
                panel.add(idItemTextField);
                panel.add(new JLabel("Nhập số lượng vật phẩm:"));
                panel.add(itemQuantityTextField);
                panel.add(new JLabel("Nhập cấp độ nâng cấp vật phẩm:"));
                panel.add(itemUpgradeTextField);
                panel.add(new JLabel("Nhập hệ thống vật phẩm:"));
                panel.add(itemSysTextField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Thêm vật phẩm cho nhân vật", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    // Lấy thông tin từ các ô nhập liệu
                    String nameUS = nameTextField.getText();
                    int idItemGFF = Integer.parseInt(idItemTextField.getText());
                    int itemQuantityGFF = Integer.parseInt(itemQuantityTextField.getText());
                    int itemUpgradeGFF = Integer.parseInt(itemUpgradeTextField.getText());
                    byte itemSysGFF = Byte.parseByte(itemSysTextField.getText());

                    // Thêm vật phẩm vào túi đồ của nhân vật
                    Char userGF = Client.gI().getNinja(nameUS);
                    if (userGF != null) {
                        Item itemGF;
                        for (byte i = 0; i < itemQuantityGFF; i++) {
                            itemGF = ItemTemplate.itemDefault(idItemGFF);
                            ItemTemplate data2 = ItemTemplate.ItemTemplateId(idItemGFF);
                            Item itemup;
                            if (data2.type < 10) {
                                if (data2.type == 1) {
                                    itemup = ItemTemplate.itemDefault(idItemGFF);
                                    itemup.sys = GameSrc.SysClass(data2.nclass);
                                } else {
                                    byte sys = itemSysGFF;
                                    itemup = ItemTemplate.itemDefault(idItemGFF, sys);
                                }
                            } else {
                                itemup = ItemTemplate.itemDefault(idItemGFF);
                            }
                            itemup.isLock = false;
                            int idOp2;
                            for (Option option : itemup.options) {
                                idOp2 = option.id;
                            }
                            itemup.upgradeNext((byte) itemUpgradeGFF);
                            userGF.addItemBag(true, itemup);
                        }
                        // Hiển thị thông báo thành công
                        JOptionPane.showMessageDialog(null, "Đã thêm " + itemQuantityGFF + " vật phẩm có ID " + idItemGFF + " cho người chơi " + nameUS, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Hiển thị thông báo lỗi nếu không tìm thấy nhân vật
                        JOptionPane.showMessageDialog(null, "Không tìm thấy nhân vật " + nameUS, "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                // Hiển thị thông báo lỗi nếu người dùng nhập sai định dạng số
                JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Hiển thị thông báo lỗi nếu có lỗi xảy ra
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Khi button 14 được click
        maintenanceButton14.addActionListener(e -> {
            try {
                JPanel panel = new JPanel(new GridLayout(0, 2));

                JTextField nameTextField = new JTextField();
                JTextField idItemTextField = new JTextField();
                JTextField itemQuantityTextField = new JTextField();

                panel.add(new JLabel("Nhập tên nhân vật:"));
                panel.add(nameTextField);
                panel.add(new JLabel("Nhập ID vật phẩm:"));
                panel.add(idItemTextField);
                panel.add(new JLabel("Nhập số lượng vật phẩm:"));
                panel.add(itemQuantityTextField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Thêm vật phẩm cho nhân vật", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    // Lấy thông tin từ các ô nhập liệu
                    String nameUS = nameTextField.getText();
                    int idItemGFF = Integer.parseInt(idItemTextField.getText());
                    int itemQuantityGFF = Integer.parseInt(itemQuantityTextField.getText());

                    // Thêm vật phẩm vào túi đồ của nhân vật
                    Char userGF = Client.gI().getNinja(nameUS);
                    if (userGF != null) {
                        Item itemGF;
                        if (itemQuantityGFF < 120) {
                            for (byte i = 0; i < itemQuantityGFF; i++) {
                                itemGF = ItemTemplate.itemDefault(idItemGFF);
                                ItemTemplate data2 = ItemTemplate.ItemTemplateId(idItemGFF);
                                Item itemup;
                                if (data2.type < 10) {
                                    if (data2.type == 1) {
                                        itemup = ItemTemplate.itemDefault(idItemGFF);
                                        itemup.sys = GameSrc.SysClass(data2.nclass);
                                    } else {
                                        byte sys = (byte) Util.nextInt(1, 3);
                                        itemup = ItemTemplate.itemDefault(idItemGFF, sys);
                                    }
                                } else {
                                    itemup = ItemTemplate.itemDefault(idItemGFF);
                                }
                                itemup.isLock = false;
                                int idOp2;
                                for (Option option : itemup.options) {
                                    idOp2 = option.id;
                                }
                                userGF.addItemBag(true, itemup);
                            }
                        } else {
                            itemGF = ItemTemplate.itemDefault(idItemGFF);
                            itemGF.quantity = itemQuantityGFF;
                            userGF.addItemBag(true, itemGF);
                        }
                        // Hiển thị thông báo thành công
                        JOptionPane.showMessageDialog(null, "Đã thêm " + itemQuantityGFF + " vật phẩm có ID " + idItemGFF + " cho người chơi " + nameUS, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Hiển thị thông báo lỗi nếu không tìm thấy nhân vật
                        JOptionPane.showMessageDialog(null, "Không tìm thấy nhân vật " + nameUS, "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                // Hiển thị thông báo lỗi nếu người dùng nhập sai định dạng số
                JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Hiển thị thông báo lỗi nếu có lỗi xảy ra
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Khi button 15 được click
        maintenanceButton15.addActionListener(e -> {
    SwingUtilities.invokeLater(() -> {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        long memoryusage = osBean.getTotalPhysicalMemorySize() / 1000000L - osBean.getFreePhysicalMemorySize() / 1000000L;
        long totalmemory = osBean.getTotalPhysicalMemorySize() / 1000000L;
        
        String message = "- Số nguời online: " + Client.gI().ninja_size()
                + "\n\n- CPU Usage: " + String.format("%.2f", osBean.getSystemCpuLoad() * 100.0) + "%"
                + "\n\n- Free Memory: " + osBean.getFreePhysicalMemorySize() / 1000000L + "MB"
                + "\n\n- Memory Usage: " + memoryusage + "MB"
                + "\n\n- Total Memory: " + totalmemory + "MB"
                + "\n\n- Even Hiện Tại: " + Server.manager.event
                + "\n\n- Có thể online: " + Server.manager.acclimitIP + " nick cùng lúc";
        
        JOptionPane.showMessageDialog(null, message);
    });
});
        
       maintenanceButton16.setText("Thay đổi Giới Hạn nick"); // Đặt tên
maintenanceButton16.addActionListener(e -> {
    String currentLimitMessage = "Giới hạn nick hiện tại: " + Server.manager.acclimitIP;
    String str = JOptionPane.showInputDialog(null, currentLimitMessage + "\n\nNhập giá trị thay đổi:", "Thay đổi nick", JOptionPane.INFORMATION_MESSAGE);
    
    if (str == null || str.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Giá trị thay đổi không hợp lệ");
        return;
    }
    String check = str.replaceAll("\\s+", "").trim();
    try {
        int gioihanip = Integer.parseInt(check);
        if (gioihanip <= 0) {
            gioihanip = 1;
        }
        Server.manager.acclimitIP = gioihanip;
        JOptionPane.showMessageDialog(null, "Thay đổi thành công - Giá trị thay đổi giới hạn " + gioihanip);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Giá trị nhập vào không hợp lệ");
    }
});

maintenanceButton17.setText("Thay đổi EVEN"); // Đặt tên
String[] eventNames = {"Không Sự Kiện", "Sự kiện hè", "Sự kiện trung thu", "Sự kiện Noel", "Sự kiện Tết", "Sự kiện 8 Tháng 3", "Sự kiện Giỗ Tổ", "Sự kiện Nước Giải Khác"};
JComboBox<String> eventComboBox = new JComboBox<>(eventNames);

JPanel panel = new JPanel();

maintenanceButton17.addActionListener(actionEvent -> {
    if (Server.manager == null) {
        Server.manager = new Manager(); // Khởi tạo đối tượng Server.manager nếu chưa tồn tại
    }

    String currentEventName = eventNames[Server.manager.event];
    String currentLimitMessage = "Event hiện tại: " + currentEventName;

    JOptionPane.showMessageDialog(null, currentLimitMessage);

    int option = JOptionPane.showOptionDialog(
        null,
        panel,
        "Chọn sự kiện:",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null,
        eventNames,
        eventNames[0]
    );

    if (option == JOptionPane.CLOSED_OPTION) {
        return;
    }

    byte eventID = (byte) option;
    Server.manager.event = eventID;
    JOptionPane.showMessageDialog(null, "Thay đổi thành công - Sự kiện được chọn: " + eventNames[option]);
});



maintenanceButton18.addActionListener(e -> {
    JTextArea textArea = new JTextArea();
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    int result = JOptionPane.showOptionDialog(
        null,
        scrollPane,
        "Nhập nội dung mới",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE,
        null,
        null,
        null
    );

    if (result == JOptionPane.OK_OPTION) {
        String newContent = textArea.getText();
        int id = 1; 
        try {
            PreparedStatement statement = SQLManager.conn.prepareStatement("UPDATE alert SET content = ? WHERE id = ?");
            statement.setString(1, newContent);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Đã cập nhật hàng thành công");
            } else {
                System.out.println("Không có hàng nào được cập nhật");
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi cập nhật hàng: " + ex.getMessage());
        }
    }
});



maintenanceButton20.addActionListener(e -> {
    JTextField npcIdField = new JTextField();
    JTextField xCoordinateField = new JTextField();
    JTextField yCoordinateField = new JTextField();
    JTextField mapIdField = new JTextField();

    Object[] message = {
        "NPC ID:", npcIdField,
        "X Coordinate:", xCoordinateField,
        "Y Coordinate:", yCoordinateField,
        "Map ID:", mapIdField
    };

    int option = JOptionPane.showOptionDialog(null, message, "Nhập dữ liệu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

    if (option == JOptionPane.OK_OPTION) {
        try {
            int npcId = Integer.parseInt(npcIdField.getText());
            int xCoordinate = Integer.parseInt(xCoordinateField.getText());
            int yCoordinate = Integer.parseInt(yCoordinateField.getText());
            int mapId = Integer.parseInt(mapIdField.getText());

            try {
                PreparedStatement selectStatement = SQLManager.conn.prepareStatement("SELECT NPC FROM `vhd_data`.`map` WHERE id = ?");
                selectStatement.setInt(1, mapId);
                ResultSet resultSet = selectStatement.executeQuery();

               String npcData = "";

                if (resultSet.next()) {
                npcData = resultSet.getString("NPC");
            }

                    if (npcData != null && !npcData.isEmpty()) {
                npcData = npcData.substring(0, npcData.length() - 1);
                npcData += ",";
            }
              npcData += String.format("[1,%d,%d,%d]]", npcId, xCoordinate, yCoordinate, mapId);
     
                PreparedStatement updateStatement = SQLManager.conn.prepareStatement("UPDATE `vhd_data`.`map` SET NPC = ? WHERE id = ?");
                updateStatement.setString(1, npcData);
                updateStatement.setInt(2, mapId);
                updateStatement.executeUpdate();
                System.out.println("Cập nhật tọa độ NPC thành công.");

                // Đóng câu lệnh
                updateStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (NumberFormatException ex) {
            String errorMessage = "Định dạng nhập không hợp lệ. Vui lòng nhập giá trị số cho ID, Tọa độ X, Tọa độ Y, và ID Bản đồ.";
            JOptionPane.showMessageDialog(null, errorMessage, "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
});
maintenanceButton21.addActionListener(e -> {
    JTextField treeIdField = new JTextField();
    JTextField xCoordinateField = new JTextField();
    JTextField yCoordinateField = new JTextField();
    JTextField mapIdField = new JTextField();

    Object[] message = {
        "RES CÂY:", treeIdField,
        "X Coordinate:", xCoordinateField,
        "Y Coordinate:", yCoordinateField,
        "Map ID:", mapIdField
    };

    int option = JOptionPane.showOptionDialog(null, message, "Nhập dữ liệu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

    if (option == JOptionPane.OK_OPTION) {
        try {
            String treeId = treeIdField.getText();
            int xCoordinate = Integer.parseInt(xCoordinateField.getText());
            int yCoordinate = Integer.parseInt(yCoordinateField.getText());
            int mapId = Integer.parseInt(mapIdField.getText());

            try {
                // Lấy dữ liệu hiện tại trong cột itemcay
                PreparedStatement selectStatement = SQLManager.conn.prepareStatement("SELECT itemcay FROM `map` WHERE id = ?");
                selectStatement.setInt(1, mapId);
                ResultSet resultSet = selectStatement.executeQuery();

                String itemcayData = "";

                if (resultSet.next()) {
                    itemcayData = resultSet.getString("itemcay");
                }
                // Kiểm tra nếu có dữ liệu trong chuỗi itemcay
                if (!itemcayData.isEmpty()) {
                     itemcayData = itemcayData.substring(0, itemcayData.length() - 1);
                    // Thêm dấu phẩy nếu đã có dữ liệu trong chuỗi
                    itemcayData += ",";
                }

                // Thêm dữ liệu mới vào chuỗi itemcay
                itemcayData += String.format("[\"%s\",%d,%d]", treeId, xCoordinate, yCoordinate);

                // Cập nhật cấu trúc itemcay trong bảng map
                PreparedStatement updateStatement = SQLManager.conn.prepareStatement("UPDATE `map` SET itemcay = ? WHERE id = ?");
                updateStatement.setString(1, itemcayData);
                updateStatement.setInt(2, mapId);
                updateStatement.executeUpdate();
                System.out.println("Cập nhật cấu trúc itemcay thành công.");

                // Đóng câu lệnh
                updateStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Xử lý các ngoại lệ khác ở đây
            }
        } catch (NumberFormatException ex) {
            String errorMessage = "Định dạng nhập không hợp lệ. Vui lòng nhập giá trị số cho ID Cây, Tọa độ X, Tọa độ Y và ID Bản đồ.";
            JOptionPane.showMessageDialog(null, errorMessage, "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
});
maintenanceButton23.addActionListener(e -> {
    Server.manager.preload();
     JOptionPane.showMessageDialog(null, "Cập Nhập thành công " );
});











    }

    void setupLayout() {
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        maintenanceButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton.getPreferredSize().height));
        maintenanceButton1.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton1.getPreferredSize().height));
        maintenanceButton2.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton2.getPreferredSize().height));
        maintenanceButton3.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton3.getPreferredSize().height));
        maintenanceButton4.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton4.getPreferredSize().height));
        maintenanceButton5.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton5.getPreferredSize().height));
        maintenanceButton6.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton6.getPreferredSize().height));
        maintenanceButton7.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton7.getPreferredSize().height));
        maintenanceButton8.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton8.getPreferredSize().height));
        maintenanceButton9.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton9.getPreferredSize().height));
        maintenanceButton11.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton11.getPreferredSize().height));
        maintenanceButton10.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton10.getPreferredSize().height));
        maintenanceButton12.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton12.getPreferredSize().height));
        maintenanceButton13.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton13.getPreferredSize().height));
        maintenanceButton14.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton14.getPreferredSize().height));
        maintenanceButton15.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton14.getPreferredSize().height));
         maintenanceButton16.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton16.getPreferredSize().height));
        maintenanceButton17.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton17.getPreferredSize().height));
        maintenanceButton18.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton18.getPreferredSize().height));
        maintenanceButton19.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton19.getPreferredSize().height));
        maintenanceButton20.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton20.getPreferredSize().height));
        maintenanceButton21.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton21.getPreferredSize().height));
        maintenanceButton22.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton22.getPreferredSize().height));
        maintenanceButton23.setMaximumSize(new Dimension(Integer.MAX_VALUE, maintenanceButton23.getPreferredSize().height));
        
    
    
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(maintenanceButton);
        buttonPanel.add(maintenanceButton1);
        buttonPanel.add(maintenanceButton2);
        buttonPanel.add(maintenanceButton3);
        buttonPanel.add(maintenanceButton4);
        buttonPanel.add(maintenanceButton5);
        buttonPanel.add(maintenanceButton6);
        buttonPanel.add(maintenanceButton7);
        buttonPanel.add(maintenanceButton8);
        buttonPanel.add(maintenanceButton9);
        buttonPanel.add(maintenanceButton11);
        buttonPanel.add(maintenanceButton10);
        buttonPanel.add(maintenanceButton12);
        buttonPanel.add(maintenanceButton13);
        buttonPanel.add(maintenanceButton14);
        buttonPanel.add(maintenanceButton15);
        buttonPanel.add(maintenanceButton16);
        buttonPanel.add(maintenanceButton17);
        buttonPanel.add(maintenanceButton18);
        buttonPanel.add(maintenanceButton19);
        buttonPanel.add(maintenanceButton20);
        buttonPanel.add(maintenanceButton21);
        buttonPanel.add(maintenanceButton22);
        buttonPanel.add(maintenanceButton23);
        buttonPanel.add(Box.createVerticalGlue());
        add(buttonPanel, BorderLayout.NORTH);
        // Đặt kích cở panel
        setPreferredSize(new Dimension(350, buttonPanel.getPreferredSize().height));
  }

    public static void start(JFrame frame, Component component, ServerSocket listenSocket, Session conn) {
    frame = new JFrame("Panel Huy Đạt");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(component);
    frame.pack();
    frame.setLocationRelativeTo(null); // Đưa cửa sổ vào giữa màn hình
    frame.setVisible(true);
}

}