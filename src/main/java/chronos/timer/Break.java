package chronos.timer;

import java.util.TimerTask;

/**
 * Represents a Break type timer, subclass of TimerTask
 */
public class Break extends TimerTask {
    /**
     * Prints out a string that informs the users that the 5-minute break timer is running
     */
    @Override
    public void run() {
        System.out.println("Break time!");
    }
}
