package huydat.itemreal;

import java.util.ArrayList;

/**
 *
 * @VŨ HUY ĐẠT
 */

public class RuongHuyenBi 
{
    public String name;
    public String item;
    public String time;
    public static ArrayList<RuongHuyenBi> RuongHB = new ArrayList<>();

    public RuongHuyenBi(String name, String item, String time) {
        this.name = name;
        this.item = item;
        this.time = time;
    }
}
