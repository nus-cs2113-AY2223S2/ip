package Duke.DukeTask;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class DukeEvent extends DukeTask {
    protected String eventFrom;
    protected String eventTo;
    protected boolean formatted;
    protected LocalDate eventFromDate;
    protected LocalDate eventToDate;

    public DukeEvent(String taskName, String eventFrom, String eventTo) {
        super(taskName);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
        try {
            this.eventFromDate = LocalDate.parse(eventFrom);
            this.eventToDate = LocalDate.parse(eventTo);
            this.formatted = true;
        } catch (Exception e) {
            this.formatted = false;
        }
    }
    @Override
    public void printTask(int id) {
        System.out.println((id +1) + ".[E] [" + this.getStatusIcon() + "] "
                + this.taskName + " (from: "
                + ((this.formatted) ? eventFromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : eventFrom)
                + " to: "
                + ((this.formatted) ? eventToDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : eventTo)
                + ")");
    }
    @Override
    public void printTask() {
        System.out.println("[E] [" + this.getStatusIcon() + "] "
                + this.taskName + " (from: "
                + ((this.formatted) ? eventFromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : eventFrom)
                + " to: "
                + ((this.formatted) ? eventToDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : eventTo)
                + ")");
    }
    @Override
    public String saveTask() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.taskName + " | "
                + this.eventFrom + " | " + this.eventTo + '\n';
    }
    @Override
    public boolean isDateMatch(LocalDate date) {
        if(!this.formatted) {
            return false;
        }
        boolean afterFrom = this.eventFromDate.isBefore(date) || this.eventFromDate.isEqual(date);
        boolean beforeTo = this.eventToDate.isAfter(date) || this.eventToDate.isEqual(date);
        return afterFrom && beforeTo;
    }
}
