package huydat.stream;

import huydat.server.Manager;

public class ClearSession implements Runnable{
    public void run() {
        try {
           Manager.isClearSession = true;
           if (Manager.isClearSession) {
               Client.gI().Clear();
               Manager.isClearSession = false;
               System.out.println("-------------------------- Đã dọn kết nối rác: "+Manager.isClearSession+" -----------------------------------" +!Thread.currentThread().isInterrupted());
                return;
            }
        } catch (Exception e) {
            Manager.isClearSession = false;
            e.printStackTrace();
            return;
        }
    }
}
