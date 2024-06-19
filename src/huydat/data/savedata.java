/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huydat.data;

/**
 *
 * @author Admin
 */

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class savedata {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/vhd_account";
        String user = "root";
        String password = "";
        String savePath = "path/to/save/vhd_account.sql";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM vhd_account")) {

            // Tạo tệp để lưu dữ liệu
            FileWriter fileWriter = new FileWriter(savePath);

            // Lưu dữ liệu vào tệp SQL
            while (resultSet.next()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    fileWriter.write(resultSet.getString(i) + "\t");
                }
                fileWriter.write("\n");
            }

            // Đóng tệp
            fileWriter.close();

            System.out.println("Dữ liệu đã được tải về thành công!");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
