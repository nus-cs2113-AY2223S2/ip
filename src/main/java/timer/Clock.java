package timer;

import java.util.Timer;

public class Clock {
    protected static int workDuration = 25 * 60 * 1000; // 25 minutes in milliseconds
    protected static int breakDuration = 5 * 60 * 1000; // 5 minutes in milliseconds
    static Timer timer;

    public Clock() {
        timer = new Timer();
    }
    public void startWork() {
        timer.schedule(new WorkTask(), 0, workDuration);
    }

    public void startBreak() {
        timer.schedule(new Break(), 0, breakDuration);
    }
    public void cancelClock() {
        timer.cancel();
    }

}
