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

    /**
     * Constructor for DukeEvent.
     * If the date is formatted, it will be parsed into LocalDate.
     * @param taskName Name of the task.
     * @param eventFrom The start date of the event.
     * @param eventTo The end date of the event.
     */
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

    /**
     * Prints the task in the format of:
     * "id. [Task Type] [Status Icon] Task Name (from: start date to: end date)".
     * If the date can be parsed into LocalDate, it will be printed in the format of MMM d yyyy.
     * @param id the id of the task in the list.
     */
    @Override
    public void printTask(int id) {
        System.out.println((id +1) + ".[E] [" + this.getStatusIcon() + "] "
                + this.taskName + " (from: "
                + ((this.formatted) ? eventFromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : eventFrom)
                + " to: "
                + ((this.formatted) ? eventToDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : eventTo)
                + ")");
    }

    /**
     * Prints the task in the format of:
     * "[Task Type] [Status Icon] Task Name (from: start date to: end date)".
     * If the date can be parsed into LocalDate, it will be printed in the format of MMM d yyyy.
     */
    @Override
    public void printTask() {
        System.out.println("[E] [" + this.getStatusIcon() + "] "
                + this.taskName + " (from: "
                + ((this.formatted) ? eventFromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : eventFrom)
                + " to: "
                + ((this.formatted) ? eventToDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : eventTo)
                + ")");
    }

    /**
     * Returns the String in the format of:
     * "E | [Status Icon] | Task Name | start date | end date".
     * @return the string representation of the event.
     */
    @Override
    public String saveTask() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.taskName + " | "
                + this.eventFrom + " | " + this.eventTo + '\n';
    }

    /**
     * Checks if the event is on the date. (in the format of yyyy-mm-dd)
     * @param date the date to be checked.
     * @return true if the date is between the start date and end date of the event.
     * If the date cannot be parsed into LocalDate, it will return false.
     */
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
