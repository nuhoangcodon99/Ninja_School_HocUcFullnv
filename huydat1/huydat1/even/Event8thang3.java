/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huydat.even;

/**
 *
 * @author Admin
 */
import huydat.io.Util;
import huydat.real.Char;
import huydat.real.Item;
import huydat.real.Player;
import huydat.server.Service;
import huydat.stream.Client;
import huydat.template.ItemTemplate;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * Vũ Huy Đạt
 */

public class Event8thang3
{
    private static final String DATE_FORMAT_FILE = "dd_MMM_yyyy";
    private static SimpleDateFormat dateFormatFile = null;
    
    public static void bohoaxanh(Player p) throws IOException {
        p.updateExp(1000000L);
        p.c.removeItemBags(391, 1);
        Char userGF = Client.gI().getNinja(p.nameUS);
        if (p.c.getBagNull() == 0) {
            p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
            return;
        } else {
            short[] arId = new short[]{12, 12, 12, 8, 9, 8, 9, 275, 276, 277, 278, 275, 7, 8, 9, 436, 437, 438, 682, 383, 382, 381, 222, 223, 224, 225, 226, 227, 228, 251, 308, 309, 548, 275, 276, 277, 278, 539, 674, 695, 696, 697, 698, 699, 674, 700, 701, 702, 703, 704, 733, 734, 735, 736, 737, 738, 739, 674, 740, 741, 760, 761, 762, 674, 763, 764, 765, 766, 767, 768, 674, 695, 696, 697, 698, 699, 674, 700, 701, 702, 703, 704, 733, 734, 735, 736, 737, 738, 739, 674, 740, 741, 760, 761, 762, 674, 763, 764, 765, 766, 767, 768, 674, 695, 696, 697, 698, 699, 674, 700, 701, 702, 703, 704, 733, 734, 735, 736, 737, 738, 739, 674, 740, 741, 760, 761, 762, 674, 763, 764, 765, 766, 767, 768, 289, 290, 291, 289, 290, 291, 289, 290, 291, 276, 277, 278, 548, 12, 548, 381, 382, 381, 382, 381, 682, 682, 682, 228, 227, 226, 225, 224, 223, 222, 283, 436, 438, 437, 436, 437, 419, 403, 419, 403, 407, 407, 12, 254, 255, 256, 12, 254, 255, 256};
            short idI = arId[Util.nextInt(arId.length)];
            Item itemup = ItemTemplate.itemDefault(idI);
            itemup.isLock = false;
            p.c.addItemBag(true, itemup);
            userGF.p.sendAddchatYellow("Bạn đã nhận được bó hoa hồng xanh từ " + p.c.name + "");
            if (itemup.id == 383) {
                Service.chatKTG("Server: (" + p.c.name + ") " + " tặng bó hoa hồng xanh may mắn nhận được bát bảo đáng ngưỡng mộ thật (o_o)"); 
            }
            System.out.println("Người chơi " + p.c.name + " tặng bó hoa hồng xanh cho " + userGF.name);
            try {
                Calendar calender = Calendar.getInstance();
                Date date = calender.getTime();
                dateFormatFile = new SimpleDateFormat(DATE_FORMAT_FILE);
                String str = Util.toDateString(Date.from(Instant.now()));
                String filename = "history/Use_Event/Use_Bohoaxanh_" + dateFormatFile.format(date) + ".txt";
                FileWriter fw = new FileWriter(filename, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("Name : " + p.c.name + " tặng bó hoa hồng xanh cho " + userGF.name + " || Time : " + str + " || Item nhận : " + idI + " \n");
                bw.close();
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public static void bohoavang(Player p) throws IOException {
        p.updateExp(1000000L);
        p.c.removeItemBags(390, 1);
        Char userGF = Client.gI().getNinja(p.nameUS);
        if (p.c.getBagNull() == 0) {
            p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
            return;
        } else {
            short[] arId = new short[]{12, 12, 12, 8, 9, 8, 9, 275, 276, 277, 278, 275, 7, 8, 9, 436, 437, 438, 682, 383, 382, 381, 222, 223, 224, 225, 226, 227, 228, 251, 308, 309, 548, 275, 276, 277, 278, 539, 674, 695, 696, 697, 698, 699, 674, 700, 701, 702, 703, 704, 733, 734, 735, 736, 737, 738, 739, 674, 740, 741, 760, 761, 762, 674, 763, 764, 765, 766, 767, 768, 674, 695, 696, 697, 698, 699, 674, 700, 701, 702, 703, 704, 733, 734, 735, 736, 737, 738, 739, 674, 740, 741, 760, 761, 762, 674, 763, 764, 765, 766, 767, 768, 674, 695, 696, 697, 698, 699, 674, 700, 701, 702, 703, 704, 733, 734, 735, 736, 737, 738, 739, 674, 740, 741, 760, 761, 762, 674, 763, 764, 765, 766, 767, 768, 289, 290, 291, 289, 290, 291, 289, 290, 291, 276, 277, 278, 548, 12, 548, 381, 382, 381, 382, 381, 682, 682, 682, 228, 227, 226, 225, 224, 223, 222, 283, 436, 438, 437, 436, 437, 419, 403, 419, 403, 407, 407, 12, 254, 255, 256, 12, 254, 255, 256};
            short idI = arId[Util.nextInt(arId.length)];
            Item itemup = ItemTemplate.itemDefault(idI);
            itemup.isLock = false;
            if (itemup.id == 383) {
                Service.chatKTG("Server: (" + p.c.name + ") " + " tặng bó hoa hồng vàng may mắn nhận được bát bảo đáng ngưỡng mộ thật (o_o)"); 
            }
            p.c.addItemBag(true, itemup);
            userGF.p.sendAddchatYellow("Bạn đã nhận được bó hoa hồng vàng từ " + p.c.name + "");
            System.out.println("Người chơi " + p.c.name + " tặng bó hoa hồng vàng " + userGF.name);
            try {
                Calendar calender = Calendar.getInstance();
                Date date = calender.getTime();
                dateFormatFile = new SimpleDateFormat(DATE_FORMAT_FILE);
                String str = Util.toDateString(Date.from(Instant.now()));
                String filename = "history/Use_Event/Use_Bohoavang_" + dateFormatFile.format(date) + ".txt";
                FileWriter fw = new FileWriter(filename, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("Name : " + p.c.name + " tặng bó hoa hồng vàng cho " + userGF.name + " || Time : " + str + " || Item nhận : " + idI + " \n");
                bw.close();
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public static void bohoado(Player p) throws IOException {
        p.updateExp(1000000L);
        p.c.removeItemBags(389, 1);
        Char userGF = Client.gI().getNinja(p.nameUS);
        if (p.c.getBagNull() == 0) {
            p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
            return;
        } else {
            short[] arId = new short[]{12, 12, 12, 8, 9, 8, 9, 275, 276, 277, 278, 275, 7, 8, 9, 436, 437, 438, 682, 383, 382, 381, 222, 223, 224, 225, 226, 227, 228, 251, 308, 309, 548, 275, 276, 277, 278, 539, 674, 695, 696, 697, 698, 699, 674, 700, 701, 702, 703, 704, 733, 734, 735, 736, 737, 738, 739, 674, 740, 741, 760, 761, 762, 674, 763, 764, 765, 766, 767, 768, 674, 695, 696, 697, 698, 699, 674, 700, 701, 702, 703, 704, 733, 734, 735, 736, 737, 738, 739, 674, 740, 741, 760, 761, 762, 674, 763, 764, 765, 766, 767, 768, 674, 695, 696, 697, 698, 699, 674, 700, 701, 702, 703, 704, 733, 734, 735, 736, 737, 738, 739, 674, 740, 741, 760, 761, 762, 674, 763, 764, 765, 766, 767, 768, 289, 290, 291, 289, 290, 291, 289, 290, 291, 276, 277, 278, 548, 12, 548, 381, 382, 381, 382, 381, 682, 682, 682, 228, 227, 226, 225, 224, 223, 222, 283, 436, 438, 437, 436, 437, 419, 403, 419, 652, 653, 654, 403, 407, 407, 408, 523, 524, 12, 254, 255, 256, 12, 254, 255, 256};
            short idI = arId[Util.nextInt(arId.length)];
            Item itemup = ItemTemplate.itemDefault(idI);
            itemup.isLock = false;
            if (itemup.id == 383) {
                Service.chatKTG("Server: (" + p.c.name + ") " + " tặng bó hoa hồng đỏ may mắn nhận được bát bảo đáng ngưỡng mộ thật (o_o)"); 
            }
            p.c.addItemBag(true, itemup);
            userGF.p.sendAddchatYellow("Bạn đã nhận được bó hoa hồng đỏ từ " + p.c.name + "");
            System.out.println("Người chơi " + p.c.name + " tặng bó hoa hồng đỏ cho " + userGF.name);
            try {
                Calendar calender = Calendar.getInstance();
                Date date = calender.getTime();
                dateFormatFile = new SimpleDateFormat(DATE_FORMAT_FILE);
                String str = Util.toDateString(Date.from(Instant.now()));
                String filename = "history/Use_Event/Use_Bohoado_" + dateFormatFile.format(date) + ".txt";
                FileWriter fw = new FileWriter(filename, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("Name : " + p.c.name + " tặng bó hoa hồng đỏ cho " + userGF.name + " || Time : " + str + " || Item nhận : " + idI + " \n");
                bw.close();
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public static void giohoa(Player p) throws IOException {
        p.updateExp(5000000L);
        p.c.removeItemBags(392, 1);
        Char userGF = Client.gI().getNinja(p.nameUS);
        if (p.c.getBagNull() == 0) {
            p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
            return;
        } else {
            short[] arId = new short[]{12, 12, 12, 8, 9, 8, 829, 9, 275, 276, 277, 278, 275, 7, 8, 9, 436, 437, 438, 682, 383, 382, 381, 222, 223, 224, 225, 226, 227, 228, 251, 308, 309, 548, 275, 276, 277, 278, 539, 674, 695, 696, 697, 698, 699, 674, 700, 701, 702, 703, 704, 733, 734, 735, 736, 737, 738, 739, 674, 740, 741, 760, 761, 762, 674, 763, 764, 765, 766, 767, 768, 674, 695, 696, 697, 698, 699, 674, 700, 701, 702, 703, 704, 733, 734, 735, 736, 737, 738, 739, 674, 740, 741, 760, 761, 762, 674, 763, 764, 765, 766, 767, 768, 674, 695, 696, 697, 698, 699, 674, 700, 701, 702, 703, 704, 733, 734, 735, 736, 737, 738, 739, 674, 740, 741, 760, 761, 762, 674, 763, 764, 765, 766, 767, 768, 289, 290, 291, 289, 290, 291, 289, 290, 291, 276, 277, 278, 548, 12, 548, 381, 382, 381, 382, 381, 682, 682, 682, 228, 227, 226, 225, 224, 223, 222, 283, 436, 438, 437, 436, 437, 419, 403, 419, 403, 407, 407, 408, 523, 524, 455, 456, 573, 454, 12, 254, 255, 256, 12, 254, 255, 256};
            short idI = arId[Util.nextInt(arId.length)];
            Item itemup = ItemTemplate.itemDefault(idI);
            if (itemup.id == 383) {
                Service.chatKTG("Server: (" + p.c.name + ") " + " tặng giỏ hoa may mắn nhận được bát bảo đáng ngưỡng mộ thật (o_o)"); 
            }
            itemup.isLock = false;
            p.c.addItemBag(true, itemup);
            userGF.p.sendAddchatYellow("Bạn đã nhận được hoa từ " + p.c.name + "");
            userGF.p.updateExp(1000000L);
            System.out.println("Người chơi " + p.c.name + " tặng giỏ hoa cho " + userGF.name);
            try {
                Calendar calender = Calendar.getInstance();
                Date date = calender.getTime();
                dateFormatFile = new SimpleDateFormat(DATE_FORMAT_FILE);
                String str = Util.toDateString(Date.from(Instant.now()));
                String filename = "history/Use_Event/Use_Giohoa" + dateFormatFile.format(date) + ".txt";
                FileWriter fw = new FileWriter(filename, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("Name : " + p.c.name + " tặng giỏ hoa cho " + userGF.name + " || Time : " + str + " || Item nhận : " + idI + " \n");
                bw.close();
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

