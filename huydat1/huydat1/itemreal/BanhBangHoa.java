package huydat.itemreal;

import java.util.ArrayList;

/**
 *
 * @VŨ HUY ĐẠT
 */

public class BanhBangHoa 
{
    public String name;
    public String item;
    public String time;
    public static ArrayList<BanhBangHoa> BanhBH = new ArrayList<>();

    public BanhBangHoa(String name, String item, String time) {
        this.name = name;
        this.item = item;
        this.time = time;
    }
}
