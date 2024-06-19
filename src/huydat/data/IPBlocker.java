/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huydat.data;

/**
 *
 * @author Admin
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class IPBlocker {
    private ArrayList<String> blockedIPs;

    public IPBlocker(String filePath) {
        blockedIPs = readBlockedIPsFromFile(filePath);
    }

    public boolean isBlocked(Socket socket) {
        String clientIP = getClientIP(socket);
        return blockedIPs.contains(clientIP);
    }

    private ArrayList<String> readBlockedIPsFromFile(String filePath) {
        ArrayList<String> blockedIPs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                blockedIPs.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return blockedIPs;
    }

    private String getClientIP(Socket socket) {
        InetAddress address = socket.getInetAddress();
        return address.getHostAddress();
    }
}
