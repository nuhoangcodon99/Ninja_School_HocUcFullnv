/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huydat.stream;
import huydat.real.Char;
import huydat.real.Map;
import huydat.server.Manager;
import huydat.server.Server;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author HUY DAT
 */
public class EventClan {
    public int iD ;
    private static int idbase = 1;
    public boolean Start;
    public boolean Finish;
    public ArrayList<Char> member;
    public long time;
    public Map[] map;
    
    public EventClan() {
        this.Start = true;
        this.Finish = false;
        this.time = 0;
        this.member = new ArrayList<>();
        this.map = new Map[1];
        this.iD = EventClan.idbase++;
    }
    
    public void Finish() { // kết thúc kích hết người chơi khỏi map và đóng map
        if (this.Finish == false) {
            Char nj;
            Map ma;
            while (this.member.size() > 0) {
                nj = this.member.remove(0);
                nj.yDun = 0;
                if(nj.isDie) {
                    try {
                        nj.p.liveFromDead();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                nj.tileMap.leave(nj.p);
                nj.p.restCave();
                ma = Manager.getMapid(27);
                for (byte k = 0; k < ma.area.length; ++k) {
                    if (ma.area[k].numplayers < ma.template.maxplayers) {
                        ma.area[k].EnterMap0(nj);
                        break;
                    }
                }
            }
            Server.manager.eventclan = false;
        }
    }
    
}
