package chronos.timer;

import java.util.TimerTask;


/**
 * Represents a WorkTask type of clock, subclass of TimerTask
 */
public class WorkTask extends TimerTask{
    /**
     * Prints out a string to inform users that the Work timer is running
     */
    @Override
    public void run() {
        System.out.println("Time to start work");
    }
}
