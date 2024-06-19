package huydat.template;

import huydat.io.Util;

public class DanhVongTemplate {
    public static int[] idDaDanhVong = new int[]{695, 696, 697, 698, 699, 700, 701, 702, 703, 704};
    public static int[] typeNV = new int[]{7, 8, 9};
   public static int[] typeDO = new int []{0,1,2,3,4,5,6,7,8,9};
    public static int[] diemdv = new int[] {5,6,7,8,9,10};
    public static int[] iddo = new int[] {Util.nextInt(94, 123),Util.nextInt(317, 336),Util.nextInt(335, 374)};
   public static String[] nameNV = new String[]{"- Tham gia vòng xoay may mắn vip %d/%d lần",
            "- Tham gia vòng xoay may mắn thường %d/%d lần",
            "- Chiến thắng %d/%d trận lôi đài",
            "- Luyện thành công đá 11: %d/%d lần",
            "- Lật hình %d/%d lần",
            "- Tăng điểm hiếu chiến %d/%d lần",
            "- Hạ gục %d/%d bù nhìn",
            "- Tiêu diệt %d/%d quái lệch 10 cấp độ.",
            "- Tiêu diệt %d/%d quái tinh anh lệch 10 cấp độ.",
            "- Tiêu diệt %d/%d quái thủ lĩnh lệch 10 cấp độ."};
    
        public static String[] nameNV1 = new String[]{"- Tham gia vòng xoay may mắn vip %d/%d lần",
            "- Tham gia vòng xoay may mắn thường %d/%d lần",
            "Chiến thắng lôi đài",
            "- Luyện thành công đá 11: %d/%d lần",
            "- Lật hình %d/%d lần",
            "- Tăng điểm hiếu chiến %d/%d lần",
            "- Hạ gục %d/%d bù nhìn",
            "Tiêu diệt quái",
            "Tiêu diệt tinh anh",
            "Tiêu diệt thủ lĩnh"};

   public static int randomdiemDV() {
        int ren = Util.nextInt(1,5);
        switch (ren) {
            case 1: {
                return 7;
            }
            case 2: {
                return 8;
            }
            case 3: {
                return 9;
                }
            case 4: {
                return 5;
            }
            case 5: {
                return 6;
            }
            default: {
                return Util.nextInt(DanhVongTemplate.diemdv.length);
            }
        }
    }
        
      public static int randomIdDo() {
          switch (Util.nextInt(1, 3)) {
              case 1: {
                  return Util.nextInt(94, 123);
              }
              case 2: {
                  return Util.nextInt(317, 336);
              }
              case 3: {
                  return Util.nextInt(335, 374);
              }
                  default: {
                   return Util.nextInt(DanhVongTemplate.iddo.length);
              }
          }
      }

   
    public static int randomNVDV() {
        int ren = Util.nextInt(1,3);
        switch (ren) {
            case 1: {
                return 7;
            }
            case 2: {
                return 8;
            }
            case 3: {
                return 9;
                }
            default: {
                return Util.nextInt(DanhVongTemplate.typeNV.length);
            }
        }
    }

    public static int randomdonvdv() {
        int ran = Util.nextInt(1, 10);
        switch (ran) {
            case 1: {
                return 1;
            }
            case 2: {
                return 2;
            }
            case 3: {
                return 3;
            }
            case 4: {
                return 4;
            }
            case 5: {
                return 5;
            }
            case 6: {
                return 6;
            }
            case 7: {
                return 7;
            }
            case 8: {
                return 8;
            }
            case 9: {
                return 9;
            }
            case 10: {
                return 0;
            }
            default: {
                return Util.nextInt(DanhVongTemplate.typeDO.length);
            }
        }
    }
    
    public static int targetTask(int type) {
        if (type != 2 && type != 7 && type != 8 && type != 9) {
            if (type == 9) {
                return 1;
            } else {
                return (type != 8) ? ((type != 7) ? ((type != 9) ? ((type != 2) ? 0 : Util.nextInt(2, 20)) : Util.nextInt(1, 5)) : Util.nextInt(99, 500)) : Util.nextInt(1, 10);
            }
        } else {
                return (type != 8) ? ((type != 7) ? ((type != 9) ? ((type != 2) ? 0 : Util.nextInt(2, 20)) : Util.nextInt(1, 3)) : Util.nextInt(99, 500)) : Util.nextInt(1, 5);
        }
    }

    public static int randomDaDanhVong() {
        int percent = Util.nextInt(101);
        if (percent >= 65 && percent <= 100) {
            return 695;
        } else if (percent >= 30 && percent < 40) {
            return 696;
        } else if (percent >= 10 && percent < 30) {
            return 697;
        } else {
            return 695;
        }
    }
}
