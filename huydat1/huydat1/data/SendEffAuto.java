/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huydat.data;

import huydat.io.Message;
import huydat.real.Map;
import huydat.real.Player;
import huydat.server.GameSrc;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class SendEffAuto 
{
    public static void sendEffectAuto(Player p, byte id, int x, int y, byte count, short timeInSec) throws IOException {
         Message m = null;
         try {
            m = new Message((byte) 122);
            m.writer();
            m.writer().writeByte(1);
            m.writer().writeByte(id);
            m.writer().writeShort(x);
            m.writer().writeShort(y);
            m.writer().writeByte(count);
            m.writer().writeShort(timeInSec);
            p.conn.sendMessage(m);
            m.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }
    
    public static void getDataImgEffAuto(Player p, byte type, byte id) {
        try {
            Message m = new Message((byte) 122);
            switch (type) {
                case 0: {
                    m.writer().writeByte(2);
                    m.writer().writeByte(id);
                    byte[] a = GameSrc.loadFile("res/huydat_effauto/x" + p.conn.zoomLevel + "/Images/" + id + ".png").toByteArray();
                    m.writer().writeInt(a.length);
                    m.writer().write(a);
                    break;
                }
                case 1: {
                    m.writer().writeByte(3);
                    m.writer().writeByte(id);
                    byte[] a = GameSrc.loadFile("res/huydat_effauto/x" + p.conn.zoomLevel + "/Data/" + id).toByteArray();
                    m.writer().writeShort(a.length);
                    m.writer().write(a);
                    break;
                }
            }
            p.conn.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void EffectAuto(Player p, Map map) throws IOException {
        if (map.id == 23) {
       //     sendEffectAuto(p, (byte) 13, 572, 1848, (byte) -1, (short) -1);
         //   sendEffectAuto(p, (byte) 12, 427, 1848, (byte) -1, (short) -1);
        }
        if (map.id == 22) {
            sendEffectAuto(p, (byte) 14, 1849, 252, (byte) -1, (short) -1);
            sendEffectAuto(p, (byte) 19, 1285, 240, (byte) -1, (short) -1);
            sendEffectAuto(p, (byte) 19, 971, 240, (byte) -1, (short) -1);
            sendEffectAuto(p, (byte) 9, 2032, 274, (byte) -1, (short) -1);
            sendEffectAuto(p, (byte) 7, 2146, 144, (byte) -1, (short) -1);
            sendEffectAuto(p, (byte) 7, 2050, 144, (byte) -1, (short) -1);
            sendEffectAuto(p, (byte) 9, 227, 162, (byte) -1, (short) -1);
            sendEffectAuto(p, (byte) 17, 2317, 192, (byte) -1, (short) -1);
            sendEffectAuto(p, (byte) 16, 2511, 192, (byte) -1, (short) -1);
            sendEffectAuto(p, (byte) 14, 184, 204, (byte) -1, (short) -1);
            sendEffectAuto(p, (byte) 19, 1427, 264, (byte) -1, (short) -1);
            sendEffectAuto(p, (byte) 19, 1649, 264, (byte) -1, (short) -1);
            
        }
    }
}

