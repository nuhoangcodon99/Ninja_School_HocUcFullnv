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
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

/**
 *
 * Vũ Huy Đạt
 */

public class Event_Trungthu 
{
    public static short[] LeaveItemEventTrungThu;
    public static short[] idItemHopBanhThuong;
    public static short[] idItemHopBanhThuongHang;
    public static short[] idItemNgoc;
    public static short[] idItemLDCTA;
    public static short[] idItemXNNLXM;
    public static short[] idItemHSHLSX;
    public static short[] idItemMNVGTKNC;
    public static short[] idItemMNSPONA;
    public static short[] idItemNC;
    public static short[] idItemPet;
    
    static {
        LeaveItemEventTrungThu = new short[] { 292, 293, 294, 295, 296, 297 };
        idItemHopBanhThuong = new short[] { 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 7, 8, 9, 242, 249, 251, 254, 255, 275, 276, 277, 278, 289, 289, 340, 340, 409, 410, 436, 436, 436, 436, 436, 437, 437, 443, 485, 524, 549, 550, 551, 569, 577};
        idItemHopBanhThuongHang = new short[] { 4, 4, 5, 5, 6, 6, 7, 7, 8, 9, 10, 11, 246, 249, 251, 254, 255, 256, 257, 275, 276, 277, 278, 289, 290, 291, 340, 340, 409, 410, 436, 436, 436, 436, 436, 436, 437, 437, 438, 459, 450, 451, 452, 453, 567, 567, 549, 550, 551, 577, 695, 733, 734, 735, 736, 737, 738, 739, 740, 741, 760, 761, 762, 763, 764, 765, 766, 767, 768, 869};
        idItemNgoc = new short[] { 652, 653, 654, 655 };
        idItemLDCTA = new short[] { 569, 419 };
        idItemXNNLXM = new short[] { 443, 485 };
        idItemHSHLSX = new short[] { 523, 524 };
        idItemMNVGTKNC = new short[] { 403, 404 };
        idItemMNSPONA = new short[] { 407, 408 };
        idItemNC = new short[] { 242, 269, 270, 283, 284 };
        idItemPet = new short[] { 742, 772, 781 };
    }
    
    public static void BanhAll(Player p, Item item, byte index) {
        ItemTemplate data = ItemTemplate.ItemTemplateId(item.id);
        byte numbagnull = p.c.getBagNull();
        switch (item.id) {
            case 298: // 💖 Bánh thập cẩm 💖
            case 299: // 💖 Bánh dẻo 💖
            case 300: // 💖 Bánh đậu xanh 💖
            case 301: {// 💖 Bánh Pía 💖
                if (Server.manager.event != 2) {
                    p.sendAddchatYellow(thongbao.END_EVENT);
                    return;
                }
                if (p.c.level < 20) {
                    p.sendAddchatYellow("Trình độ của bạn không đủ để sử dụng vật phẩm này.");
                    return;
                }
                p.updateExp(300000L);
                p.c.removeItemBag(index, 1);
                break;
            }
        }
    }
    
    public static void HopBanhThuong(Player p, Item item, byte index) {
        ItemTemplate data = ItemTemplate.ItemTemplateId(item.id);
        byte numbagnull = p.c.getBagNull();
        switch (item.id) {
            case 302: { //Hộp bánh thường
                if (Server.manager.event != 2) {
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
                p.updateExp(500000L);
                if (Util.nextInt(10) < 3) {
                    p.updateExp(100000L);
                } else if (Util.nextInt(50) <= 1) {// 💖 Ngọc 💖
                    final short idI = Event_Trungthu.idItemNgoc[Util.nextInt(Event_Trungthu.idItemNgoc.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                } else {
                    short idI = Event_Trungthu.idItemHopBanhThuong[Util.nextInt(Event_Trungthu.idItemHopBanhThuong.length)];
                    Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = item.isLock;
                    p.c.addItemBag(true, itemup);
                }
                break;
            }
        }
    }
                    
    public static void HopBanhThuongHang(Player p, Item item, byte index) {
        ItemTemplate data = ItemTemplate.ItemTemplateId(item.id);
        byte numbagnull = p.c.getBagNull();
        switch (item.id) {
            case 303: {// Hộp bánh thượng hạng
                if (Server.manager.event != 2) {
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
                } else if (Util.nextInt(20) <= 1) {// 💖 Ngọc 💖
                    final short idI = Event_Trungthu.idItemNgoc[Util.nextInt(Event_Trungthu.idItemNgoc.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                } else if (Util.nextInt(210) <= 1) {// 💖 Lồng đèn cá chép, Chim tinh anh 💖
                    final short idI = Event_Trungthu.idItemLDCTA[Util.nextInt(Event_Trungthu.idItemLDCTA.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                } else if (Util.nextInt(190) <= 1) {// 💖 Xích Nhãn Ngân Lang, Xe máy 💖
                    final short idI = Event_Trungthu.idItemXNNLXM[Util.nextInt(Event_Trungthu.idItemXNNLXM.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                } else if (Util.nextInt(200) <= 1) {// 💖 Huyết Sắc Hung Lang, Harley Davidson 💖
                    final short idI = Event_Trungthu.idItemHSHLSX[Util.nextInt(Event_Trungthu.idItemHSHLSX.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                } else if (Util.nextInt(50) <= 1) {// 💖 Mặt nạ Vegeta, Kunoichi 💖
                    final short idI = Event_Trungthu.idItemMNVGTKNC[Util.nextInt(Event_Trungthu.idItemMNVGTKNC.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                } else if (Util.nextInt(100) <= 1) {// 💖 Mặt nạ Super Broly, Onna Bugeisha 💖
                    final short idI = Event_Trungthu.idItemMNSPONA[Util.nextInt(Event_Trungthu.idItemMNSPONA.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                } else if (Util.nextInt(150) <= 1) {// 💖 ALL NC 💖
                    final short idI = Event_Trungthu.idItemNC[Util.nextInt(Event_Trungthu.idItemNC.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    p.c.addItemBag(false, itemup);
                    break;
                } else if (Util.nextInt(300) <= 1) {// 💖 Pet 💖
                    final short idI = Event_Trungthu.idItemPet[Util.nextInt(Event_Trungthu.idItemPet.length)];
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
                    Manager.chatKTG("Người chơi: " + p.c.name + " sử dụng " + data.name + " nhận được " + ItemTemplate.ItemTemplateId(252).name + " thật đáng ngưỡng mộ");
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
                } else {
                    final short idI = Event_Trungthu.idItemHopBanhThuongHang[Util.nextInt(Event_Trungthu.idItemHopBanhThuongHang.length)];
                    final Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = item.isLock;
                    p.c.addItemBag(true, itemup);
                }
                break;
            }
        }
    }
    
    public static void LongDen(Player p, Item item, byte index) throws IOException {
        ItemTemplate data = ItemTemplate.ItemTemplateId(item.id);
        byte numbagnull = p.c.getBagNull();
        switch (item.id) {
            case 664: {//long den
                Item itemup;
                int a = Util.nextInt(200);
                if (a < 60) {
                    p.updateExp(1000000);
                } else if (a >= 60 && a < 100) {
                    p.c.upyenMessage(1000);
                } else if (a == 100) {
                    final short[] arId = {9,10,11,443,535,536,799,485,524,308,309,798,539,540,284,285,490,491,567,383,407,408,397,398,399,400,401,402,38,569};
                    short idI = arId[Util.nextInt(arId.length)];
                    itemup = ItemTemplate.itemDefault(idI);
                    p.c.addItemBag(true, itemup);
                    if (idI == 383 || idI == 384 || idI == 385 || idI == 308 || idI == 309 || idI == 535 || idI == 524 || idI == 799 || idI == 11 || idI == 10 || idI == 536 || idI == 798 || idI == 832 || idI == 830) {
                        Manager.chatKTG(p.c.name + " đã may mắn thả lồng đèn nhận được " + ItemTemplate.ItemTemplateId(idI).name);
                    }
                } else {
                    final short[] arId = {3,4,5,4,4,5,5,4,4,449,450,451,452,453,449,450,451,452,453,
                        5,5,4,4,4,5,4,6,5,4,4,449,450,451,452,453,30,249,250,449,450,451,452,453,3,4,5,6,7,275,276,3,4,5,6,7,277,3,4,5,6,7,278,3,4,5,6,7,283,3,4,5,6,7,375,3,4,5,6,7,376,377,3,4,5,6,7,378,449,450,451,452,453,449,450,451,452,453,379,3,4,449,450,451,452,453,449,450,451,449,450,451,452,453,449,450,451,452,453,452,453,5,6,7,380,409,3,4,5,6,7,3,4,5,6,7,410,436,3,4,5,6,7,3,4,5,6,7,437,438,3,4,5,6,7,449,450,451,452,453,449,450,451,452,453,3,4,5,6,7,3,4,5,6,7,3,4,449,450,451,452,453,449,450,451,452,453,5,6,7,449,450,451,3,4,5,6,7,3,4,5,6,7,452,453,3,4,5,6,7,3,4,5,6,7,454,3,4,5,6,7,3,4,5,6,7,3,4,5,6,7,3,4,5,6,7,545,567,3,4,5,6,7,3,4,5,6,7,568,3,4,5,6,7,3,4,5,6,7,570,571,3,4,5,6,7,3,4,5,6,7,573,574,575,3,4,5,6,7,3,4,5,6,7,576,577,3,4,5,6,7,449,450,451,452,453,449,450,451,452,453,3,4,5,6,449,450,451,452,453,449,450,451,452,453,7,578,695,696,3,4,5,449,450,451,452,453,449,450,451,452,453,6,7,3,4,5,6,7,3,4,5,6,7,3,4,5,6,7,775,3,4,5,6,7,3,4,5,6,7,778,779,3,4,5,6,7,3,4,5,6,7,788,789};
                    short idI = arId[Util.nextInt(arId.length)];
                    itemup = ItemTemplate.itemDefault(idI);
                    p.c.addItemBag(true, itemup);
                }
                p.c.removeItemBag(index, 1);
         //       SendEffauto.sendEffectAuto(p, (byte) 7, (int) p.c.x, (int) p.c.y, (byte) 1, (short) 1);
                break;
            }
        }
    }
}