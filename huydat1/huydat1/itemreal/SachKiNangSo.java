package huydat.itemreal;

import java.util.ArrayList;

/**
 *
 * @VŨ HUY ĐẠT
 */

public class SachKiNangSo 
{
    public String name;
    public String item;
    public String time;
    public static ArrayList<SachKiNangSo> SachKNS = new ArrayList<>();

    public SachKiNangSo(String name, String item, String time) {
        this.name = name;
        this.item = item;
        this.time = time;
    }
}
