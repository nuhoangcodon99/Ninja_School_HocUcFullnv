package huydat.even;


import huydat.data.thongbao;
import huydat.io.Util;
import huydat.server.Manager;
import huydat.server.Server;
import huydat.template.ItemTemplate;
import huydat.itemreal.BanhBangHoa;
import huydat.itemreal.BanhPhongLoi;
import huydat.itemreal.BatBao;
import huydat.itemreal.RuongBachNgan;
import huydat.itemreal.RuongHuyenBi;
import huydat.itemreal.SachKiNangSo;
import huydat.itemreal.SachTiemNangSo;
import huydat.real.Item;
import huydat.real.Player;
import java.time.Instant;
import java.util.Date;

/**
 *
 * Vũ Huy Đạt
 */

public class Event_Noel 
{
    public static short[] LeaveItemEventNoel;
    public static short[] idItemBanhChocolate;
    public static short[] idItemBanhDauTay;
    public static short[] idItemNgoc;
    public static short[] idItemLDCTA;
    public static short[] idItemXNNLXM;
    public static short[] idItemHSHLSX;
    public static short[] idItemMNVGTKNC;
    public static short[] idItemMNSPONA;
    public static short[] idItemNC;
    public static short[] idItemPet;

    static {
        LeaveItemEventNoel = new short[] { 666, 667, 668 };
        idItemBanhChocolate = new short[] { 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 7, 8, 9, 242, 249, 251, 254, 255, 275, 276, 277, 278, 289, 289, 340, 340, 409, 410, 436, 436, 436, 436, 436, 437, 437, 443, 485, 524, 549, 550, 551, 569, 577, 836, 695};
        idItemBanhDauTay = new short[] {7,7,7,8,8,9,7,7,7,8,8,9,255,256,283,775,788,789,255,256,283,775,788,789,776,779,470,471,472,419,776,779,470,471,472,567,410,799,800,567,410,799,800,397,398,399,400,246, 249, 251, 254, 255, 256, 257, 275, 276, 277, 278, 289, 290, 291, 340, 340, 409, 410, 436, 436, 437, 437, 438, 449, 450, 459, 450, 451, 452, 453, 567, 567, 549, 550, 551, 577, 598, 599, 600, 656, 657, 658, 659, 695, 696, 733, 734, 735, 736, 737, 738, 739, 740, 741, 760, 761, 762, 763, 764, 765, 766, 767, 768, 833, 835, 836};
        idItemNgoc = new short[] { 652, 653, 654, 655 };
        idItemLDCTA = new short[] { 569, 419 };
        idItemXNNLXM = new short[] { 443, 485 };
        idItemHSHLSX = new short[] { 523, 524 };
        idItemMNVGTKNC = new short[] { 403, 404 };
        idItemMNSPONA = new short[] { 407, 408 };
        idItemNC = new short[] { 242, 269, 270, 283, 284 };
        idItemPet = new short[] { 742, 772, 781 };
    }
    
    public static void BanhChocolate(Player p, Item item, byte index) {
        ItemTemplate data = ItemTemplate.ItemTemplateId(item.id);
        Item itemup;
        switch (item.id) {
            case 671: {
                if (Server.manager.event != 3) {
                    p.sendAddchatYellow(thongbao.END_EVENT);
                    return;
                }
                if (p.c.getBagNull() == 0) {
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
                    p.updateExp(100000L);
                } else if (Util.nextInt(50) <= 1) {// 💖 Ngọc 💖
                    short idI = Event_Noel.idItemNgoc[Util.nextInt(Event_Noel.idItemNgoc.length)];
                    itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    break;
                } else {
                    short idI = Event_Noel.idItemBanhChocolate[Util.nextInt(Event_Noel.idItemBanhChocolate.length)];
                    itemup = ItemTemplate.itemDefault(idI);
                   // itemup.isLock = item.isLock;
                    p.c.addItemBag(true, itemup);
                }
                break;
            }
        }
    }
    
    public static void BanhDauTay(Player p, Item item, byte index) {
        ItemTemplate data = ItemTemplate.ItemTemplateId(item.id);
        Item itemup;
        switch (item.id) {
            case 672: {
                if (Server.manager.event != 3) {
                    p.sendAddchatYellow(thongbao.END_EVENT);
                    return;
                }
                if (p.c.getBagNull() == 0) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống.");
                    return;
                }
                if (p.c.level < 20) {
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
                p.updateExp(2000000L);
                if (Util.nextInt(10) < 3) {
                    p.updateExp(300000L);
                }
                else if (Util.nextInt(20) <= 1) {// 💖 Ngọc 💖
                    short idI = Event_Noel.idItemNgoc[Util.nextInt(Event_Noel.idItemNgoc.length)];
                    itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    break;
                }
                else if (Util.nextInt(210) <= 1) {// 💖 Lồng đèn cá chép, Chim tinh anh 💖
                    short idI = Event_Noel.idItemLDCTA[Util.nextInt(Event_Noel.idItemLDCTA.length)];
                    itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    break;
                }
                else if (Util.nextInt(190) <= 1) {// 💖 Xích Nhãn Ngân Lang, Xe máy 💖
                    short idI = Event_Noel.idItemXNNLXM[Util.nextInt(Event_Noel.idItemXNNLXM.length)];
                    itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    break;
                }
                else if (Util.nextInt(200) <= 1) {// 💖 Huyết Sắc Hung Lang, Harley Davidson 💖
                    short idI = Event_Noel.idItemHSHLSX[Util.nextInt(Event_Noel.idItemHSHLSX.length)];
                    itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    break;
                }
                else if (Util.nextInt(50) <= 1) {// 💖 Mặt nạ Vegeta, Kunoichi 💖
                    short idI = Event_Noel.idItemMNVGTKNC[Util.nextInt(Event_Noel.idItemMNVGTKNC.length)];
                    itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    break;
                }
                else if (Util.nextInt(100) <= 1) {// 💖 Mặt nạ Super Broly, Onna Bugeisha 💖
                    short idI = Event_Noel.idItemMNSPONA[Util.nextInt(Event_Noel.idItemMNSPONA.length)];
                    itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    break;
                }
                else if (Util.nextInt(150) <= 1) {// 💖 ALL NC 💖
                    short idI = Event_Noel.idItemNC[Util.nextInt(Event_Noel.idItemNC.length)];
                    itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    break;
                }
                /*else if (Util.nextInt(300) <= 1) {// 💖 Pet 💖
                    short idI = Event_Noel.idItemPet[Util.nextInt(Event_Noel.idItemPet.length)];
                    itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    break;
                }*/
                else if (perSKNS == Util.nextInt(350)) {// 💖 Sách kỹ năng sơ cấp 💖
                    itemup = new Item();
                    itemup.id = 252;
                    itemup.quantity = 1;
                    itemup.isExpires = false;
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    Manager.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(252).name + " thật đáng ngưỡng mộ");
                    SachKiNangSo.SachKNS.add(new SachKiNangSo(p.c.name, ItemTemplate.ItemTemplateId(252).name, Util.toDateString(Date.from(Instant.now()))));
                    break;
                }
                else if (perSTNS == Util.nextInt(350)) {// 💖 Sách kỹ năng sơ cấp 💖
                    itemup = new Item();
                    itemup.id = 253;
                    itemup.quantity = 1;
                    itemup.isExpires = false;
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    Manager.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(253).name + " thật đáng ngưỡng mộ");
                    SachTiemNangSo.SachTNS.add(new SachTiemNangSo(p.c.name, ItemTemplate.ItemTemplateId(253).name, Util.toDateString(Date.from(Instant.now()))));
                    break;
                }
                else if (perPL == Util.nextInt(350)) {// 💖 Bánh trung thu phong lôi 💖
                    itemup = new Item();
                    itemup.id = 308;
                    itemup.quantity = 1;
                    itemup.isExpires = false;
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    Manager.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(308).name + " thật đáng ngưỡng mộ");
                    BanhPhongLoi.BanhPL.add(new BanhPhongLoi(p.c.name, ItemTemplate.ItemTemplateId(308).name, Util.toDateString(Date.from(Instant.now()))));
                    break;
                }
                else if (perBH == Util.nextInt(350)) {// 💖 Bánh trung thu băng hỏa 💖
                    itemup = new Item();
                    itemup.id = 309;
                    itemup.quantity = 1;
                    itemup.isExpires = false;
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    Manager.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(309).name + " thật đáng ngưỡng mộ");
                    BanhBangHoa.BanhBH.add(new BanhBangHoa(p.c.name, ItemTemplate.ItemTemplateId(309).name, Util.toDateString(Date.from(Instant.now()))));
                    break;
                }
                else if (perBB == Util.nextInt(350)) {// 💖 Bát bảo 💖
                    itemup = new Item();
                    itemup.id = 383;
                    itemup.quantity = 1;
                    itemup.isExpires = false;
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    Manager.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(383).name + " thật đáng ngưỡng mộ");
                    BatBao.bb.add(new BatBao(p.c.name, ItemTemplate.ItemTemplateId(383).name, Util.toDateString(Date.from(Instant.now()))));
                    break;
                }
                else if (perRBN == Util.nextInt(1450)) {// 💖 Rương bạch ngân 💖
                    itemup = new Item();
                    itemup.id = 384;
                    itemup.quantity = 1;
                    itemup.isExpires = false;
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    Manager.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(384).name + " may mắn thật sự");
                    RuongBachNgan.RuongBN.add(new RuongBachNgan(p.c.name, ItemTemplate.ItemTemplateId(384).name, Util.toDateString(Date.from(Instant.now()))));
                    break;
                }
                else if (perRHB == Util.nextInt(10000)) {// 💖 Rương Huyền bí 💖
                    itemup = new Item();
                    itemup.id = 385;
                    itemup.quantity = 1;
                    itemup.isExpires = false;
                    itemup.isLock = false;
                    p.c.addItemBag(true, itemup);
                    Manager.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(385).name + " thật kinh khủng");
                    RuongHuyenBi.RuongHB.add(new RuongHuyenBi(p.c.name, ItemTemplate.ItemTemplateId(385).name, Util.toDateString(Date.from(Instant.now()))));
                    break;
                }
                else {
                    short idI = Event_Noel.idItemBanhDauTay[Util.nextInt(Event_Noel.idItemBanhDauTay.length)];
                    itemup = ItemTemplate.itemDefault(idI);
                   // itemup.isLock = item.isLock;
                    p.c.addItemBag(true, itemup);
                }
                break;
            }
        }
    }
}