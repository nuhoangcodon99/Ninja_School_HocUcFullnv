package huydat.itemreal;

import java.util.ArrayList;

/**
 *
 * @VŨ HUY ĐẠT
 */

public class SachTiemNangSo 
{
    public String name;
    public String item;
    public String time;
    public static ArrayList<SachTiemNangSo> SachTNS = new ArrayList<>();

    public SachTiemNangSo(String name, String item, String time) {
        this.name = name;
        this.item = item;
        this.time = time;
    }
}
