package chronos.timer;

import java.util.TimerTask;


/**
 * 
 */
public class WorkTask extends TimerTask{
    /**
     *
     */
    @Override
    public void run() {
        System.out.println("Time to start work");
    }
}
