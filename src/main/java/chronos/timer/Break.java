package chronos.timer;

import java.util.TimerTask;

public class Break extends TimerTask{
    @Override
    public void run() {
        System.out.println("Break time!");
    }
}
