package chronos.timer;

import java.util.Timer;

public class Clock {

    /** workDuration is the time taken for the user to be productive*/
    protected static int workDuration = 25 * 60 * 1000; // 25 minutes in milliseconds
    /** breakDuration is the time taken for the user to rest after they trigger the break timer*/
    protected static int breakDuration = 5 * 60 * 1000; // 5 minutes in milliseconds
    static Timer timer;

    /**
     * Initialises a new Clock object
     */
    public Clock() {
        timer = new Timer();
    }

    /**
     * Triggers the 25 minutes work timer
     */
    public void startWork() {
        timer.schedule(new WorkTask(), 0, workDuration);
    }

    /**
     * Triggers the 5 minutes break timer
     */
    public void startBreak() {
        timer.schedule(new Break(), 0, breakDuration);
    }

    /**
     * Cancels all clocks and application would resume as per normal
     */
    public void cancelClock() {
        timer.cancel();
    }

}
