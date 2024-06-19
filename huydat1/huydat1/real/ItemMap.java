package huydat.real;

public class ItemMap {
    public short x;
    public short y;
    public short itemMapId;
    public long removedelay = 25000L + System.currentTimeMillis();
    public int master = -1;
    public Item item;
    public int checkMob = 0;
    private boolean visible = true;
     public long nextTimeRefresh;

    public ItemMap setRemovedelay(long removedelay) {
        this.removedelay = removedelay;
        return this;
    }
    public ItemMap setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }
}
