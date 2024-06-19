package huydat.itemreal;

import java.util.ArrayList;

/**
 *
 * @VŨ HUY ĐẠT
 */

public class RuongBachNgan 
{
    public String name;
    public String item;
    public String time;
    public static ArrayList<RuongBachNgan> RuongBN = new ArrayList<>();

    public RuongBachNgan(String name, String item, String time) {
        this.name = name;
        this.item = item;
        this.time = time;
    }
}
