/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huydat.even;

import huydat.data.thongbao;
import huydat.io.Util;
import huydat.itemreal.BanhBangHoa;
import huydat.itemreal.BanhPhongLoi;
import huydat.itemreal.BatBao;
import huydat.itemreal.RuongBachNgan;
import huydat.itemreal.RuongHuyenBi;
import huydat.itemreal.SachKiNangSo;
import huydat.itemreal.SachTiemNangSo;
import huydat.real.Char;
import huydat.real.Item;
import huydat.real.Player;
import huydat.server.Manager;
import huydat.server.Server;
import huydat.server.Service;
import huydat.stream.Client;
import huydat.template.ItemTemplate;
import java.time.Instant;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Event_20_10 
{
    public static short[] idItemBoHongXanh;
    public static short[] idItemBoHongVang;
    public static short[] idItemBoHongDo;
    public static short[] idItemNgoc;
    public static short[] idItemLDCTA;
    public static short[] idItemXNNLXM;
    public static short[] idItemHSHLSX;
    public static short[] idItemMNVGTKNC;
    public static short[] idItemMNSPONA;
    public static short[] idItemNC;
    public static short[] idItemPet;
    
    static {
        idItemBoHongXanh = new short[] { 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 7, 8, 9, 242, 249, 251, 254, 255, 275, 276, 277, 278, 289, 289, 340, 340, 409, 410, 436, 436, 436, 436, 436, 437, 437, 443, 485, 524, 549, 550, 551, 569, 577};
        idItemBoHongVang = new short[] { 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 7, 8, 9, 242, 249, 251, 254, 255, 275, 276, 277, 278, 289, 289, 340, 340, 409, 410, 436, 436, 436, 436, 436, 437, 437, 443, 485, 524, 549, 550, 551, 569, 577};
        idItemBoHongDo = new short[] { 4, 4, 5, 5, 6, 6, 7, 7, 8, 9, 10, 11, 246, 249, 251, 254, 255, 256, 257, 275, 276, 277, 278, 289, 290, 291, 340, 340, 409, 410, 436, 436, 436, 436, 436, 436, 437, 437, 438, 459, 450, 451, 452, 453, 567, 567, 549, 550, 551, 577, 695, 733, 734, 735, 736, 737, 738, 739, 740, 741, 760, 761, 762, 763, 764, 765, 766, 767, 768, 869};
        idItemNgoc = new short[] { 652, 653, 654, 655 };
        idItemLDCTA = new short[] { 569, 419 };
        idItemXNNLXM = new short[] { 443, 485 };
        idItemHSHLSX = new short[] { 523, 524 };
        idItemMNVGTKNC = new short[] { 403, 404 };
        idItemMNSPONA = new short[] { 407, 408 };
        idItemNC = new short[] { 242, 269, 270, 283, 284 };
        idItemPet = new short[] { 742, 772, 781 };
    }
    
    public static void BoHongXanh(Player p, Item item, byte index) {
        ItemTemplate data = ItemTemplate.ItemTemplateId(item.id);
        byte numbagnull = p.c.getBagNull();
        switch (item.id) {
            case 391: {
                if (Server.manager.event != 6) {
                    p.sendAddchatYellow(thongbao.END_EVENT);
                    return;
                }
                if (numbagnull == 0) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống.");
                    return;
                }
                if (p.c.level < 20) {
                    p.sendAddchatYellow("Trình độ của bạn không đủ để sử dụng vật phẩm này.");
                    return;
                }
                p.c.removeItemBag(index, 1);
                p.updateExp(500000L);
                if (Util.nextInt(10) < 1) {
                    p.updateExp(1000000L);
                } else if (Util.nextInt(50) <= 1) {// 💖 Ngọc 💖
                    final short idI = Event_20_10.idItemNgoc[Util.nextInt(Event_20_10.idItemNgoc.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                } else {
                    final short idI = Event_20_10.idItemBoHongXanh[Util.nextInt(Event_20_10.idItemBoHongXanh.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = item.isLock;
                    p.c.addItemBag(true, itemup);
                }
                break;
            }
        }
    }
    
    public static void BoHongVang(Player p, Item item, byte index) {
        ItemTemplate data = ItemTemplate.ItemTemplateId(item.id);
        byte numbagnull = p.c.getBagNull();
        switch (item.id) {
            case 390: {
                if (Server.manager.event != 6) {
                    p.sendAddchatYellow(thongbao.END_EVENT);
                    return;
                }
                if (numbagnull == 0) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống.");
                    return;
                }
                if (p.c.level < 20) {
                    p.sendAddchatYellow("Trình độ của bạn không đủ để sử dụng vật phẩm này.");
                    return;
                }
                p.c.removeItemBag(index, 1);
                p.updateExp(500000L);
                if (Util.nextInt(10) < 1) {
                    p.updateExp(1000000L);
                } else if (Util.nextInt(50) <= 1) {// 💖 Ngọc 💖
                    final short idI = Event_20_10.idItemNgoc[Util.nextInt(Event_20_10.idItemNgoc.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                } else {
                    final short idI = Event_20_10.idItemBoHongVang[Util.nextInt(Event_20_10.idItemBoHongVang.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = item.isLock;
                    p.c.addItemBag(true, itemup);
                }
                break;
            }
        }
    }
    
    public static void BoHongDo(Player p, Item item, byte index) {
        ItemTemplate data = ItemTemplate.ItemTemplateId(item.id);
        byte numbagnull = p.c.getBagNull();
        switch (item.id) {
            case 389: {
                if (Server.manager.event != 6) {
                    p.sendAddchatYellow(thongbao.END_EVENT);
                    return;
                }
                if (numbagnull == 0) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống.");
                    return;
                }
                if (p.c.level < 30) {
                    p.sendAddchatYellow("Trình độ của bạn không đủ để sử dụng vật phẩm này.");
                    return;
                }
                p.c.removeItemBag(index, 1);
                int perSKNS = Util.nextInt(500);
                int perSTNS = Util.nextInt(500);
                int perBH = Util.nextInt(500);
                int perPL = Util.nextInt(500);
                int perBB = Util.nextInt(500);
                int perRBN = Util.nextInt(1500);
                int perRHB = Util.nextInt(15000);
                p.updateExp(1000000L);
                if (Util.nextInt(10) < 1) {
                    p.updateExp(3000000L);
                }
                else if (Util.nextInt(20) <= 1) {// 💖 Ngọc 💖
                    final short idI = Event_20_10.idItemNgoc[Util.nextInt(Event_20_10.idItemNgoc.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                }
                else if (Util.nextInt(210) <= 1) {// 💖 Lồng đèn cá chép, Chim tinh anh 💖
                    final short idI = Event_20_10.idItemLDCTA[Util.nextInt(Event_20_10.idItemLDCTA.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                }
                else if (Util.nextInt(190) <= 1) {// 💖 Xích Nhãn Ngân Lang, Xe máy 💖
                    final short idI = Event_20_10.idItemXNNLXM[Util.nextInt(Event_20_10.idItemXNNLXM.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                }
                else if (Util.nextInt(200) <= 1) {// 💖 Huyết Sắc Hung Lang, Harley Davidson 💖
                    final short idI = Event_20_10.idItemHSHLSX[Util.nextInt(Event_20_10.idItemHSHLSX.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                }
                else if (Util.nextInt(50) <= 1) {// 💖 Mặt nạ Vegeta, Kunoichi 💖
                    final short idI = Event_20_10.idItemMNVGTKNC[Util.nextInt(Event_20_10.idItemMNVGTKNC.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                }
                else if (Util.nextInt(100) <= 1) {// 💖 Mặt nạ Super Broly, Onna Bugeisha 💖
                    final short idI = Event_20_10.idItemMNSPONA[Util.nextInt(Event_20_10.idItemMNSPONA.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                }
                else if (Util.nextInt(150) <= 1) {// 💖 ALL NC 💖
                    final short idI = Event_20_10.idItemNC[Util.nextInt(Event_20_10.idItemNC.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                }
                else if (Util.nextInt(300) <= 1) {// 💖 Pet 💖
                    final short idI = Event_20_10.idItemPet[Util.nextInt(Event_20_10.idItemPet.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                }
                else if (perSKNS == Util.nextInt(350)) {// 💖 Sách kỹ năng sơ cấp 💖
                    Item itemup = new Item();
                    itemup.id = 252;
                    itemup.quantity = 1;
                    itemup.isExpires = false;
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    Service.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(252).name + " thật đáng ngưỡng mộ");
                    SachKiNangSo.SachKNS.add(new SachKiNangSo(p.c.name, ItemTemplate.ItemTemplateId(252).name, Util.toDateString(Date.from(Instant.now()))));
                    break;
                }
                else if (perSTNS == Util.nextInt(350)) {// 💖 Sách kỹ năng sơ cấp 💖
                    Item itemup = new Item();
                    itemup.id = 253;
                    itemup.quantity = 1;
                    itemup.isExpires = false;
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    Manager.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(253).name + " thật đáng ngưỡng mộ");
                    SachTiemNangSo.SachTNS.add(new SachTiemNangSo(p.c.name, ItemTemplate.ItemTemplateId(253).name, Util.toDateString(Date.from(Instant.now()))));
                    break;
                }
                else if (perPL == Util.nextInt(350)) {// 💖 Bánh trung thu phong lôi 💖
                    Item itemup = new Item();
                    itemup.id = 308;
                    itemup.quantity = 1;
                    itemup.isExpires = false;
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    Manager.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(308).name + " thật đáng ngưỡng mộ");
                    BanhPhongLoi.BanhPL.add(new BanhPhongLoi(p.c.name, ItemTemplate.ItemTemplateId(308).name, Util.toDateString(Date.from(Instant.now()))));
                    break;
                }
                else if (perBH == Util.nextInt(350)) {// 💖 Bánh trung thu băng hỏa 💖
                    Item itemup = new Item();
                    itemup.id = 309;
                    itemup.quantity = 1;
                    itemup.isExpires = false;
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    Manager.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(309).name + " thật đáng ngưỡng mộ");
                    BanhBangHoa.BanhBH.add(new BanhBangHoa(p.c.name, ItemTemplate.ItemTemplateId(309).name, Util.toDateString(Date.from(Instant.now()))));
                    break;
                }
                else if (perBB == Util.nextInt(350)) {// 💖 Bát bảo 💖
                    Item itemup = new Item();
                    itemup.id = 383;
                    itemup.quantity = 1;
                    itemup.isExpires = false;
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    Manager.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(383).name + " thật đáng ngưỡng mộ");
                    BatBao.bb.add(new BatBao(p.c.name, ItemTemplate.ItemTemplateId(383).name, Util.toDateString(Date.from(Instant.now()))));
                    break;
                }
                else if (perRBN == Util.nextInt(1450)) {// 💖 Rương bạch ngân 💖
                    Item itemup = new Item();
                    itemup.id = 384;
                    itemup.quantity = 1;
                    itemup.isExpires = false;
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    Manager.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(384).name + " may mắn thật sự");
                    RuongBachNgan.RuongBN.add(new RuongBachNgan(p.c.name, ItemTemplate.ItemTemplateId(384).name, Util.toDateString(Date.from(Instant.now()))));
                    break;
                }
                else if (perRHB == Util.nextInt(10000)) {// 💖 Rương Huyền bí 💖
                    Item itemup = new Item();
                    itemup.id = 385;
                    itemup.quantity = 1;
                    itemup.isExpires = false;
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    Manager.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(385).name + " thật kinh khủng");
                    RuongHuyenBi.RuongHB.add(new RuongHuyenBi(p.c.name, ItemTemplate.ItemTemplateId(385).name, Util.toDateString(Date.from(Instant.now()))));
                    break;
                }
                else {
                    final short idI = Event_20_10.idItemBoHongDo[Util.nextInt(Event_20_10.idItemBoHongDo.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = item.isLock;
                    p.c.addItemBag(true, itemup);
                }
                break;
            }
        }
    }
}
