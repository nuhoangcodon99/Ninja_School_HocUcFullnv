package huydat.itemreal;

import java.util.ArrayList;

/**
 *
 * @VŨ HUY ĐẠT
 */

public class BanhPhongLoi 
{
    public String name;
    public String item;
    public String time;
    public static ArrayList<BanhPhongLoi> BanhPL = new ArrayList<>();

    public BanhPhongLoi(String name, String item, String time) {
        this.name = name;
        this.item = item;
        this.time = time;
    }
}
