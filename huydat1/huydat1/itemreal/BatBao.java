package huydat.itemreal;

import java.util.ArrayList;

/**
 *
 * @VŨ HUY ĐẠT
 */

public class BatBao 
{
    public String name;
    public String item;
    public String time;
    public static ArrayList<BatBao> bb = new ArrayList<>();

    public BatBao(String name, String item, String time) {
        this.name = name;
        this.item = item;
        this.time = time;
    }
}
