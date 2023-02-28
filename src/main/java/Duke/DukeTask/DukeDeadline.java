package Duke.DukeTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class DukeDeadline extends DukeTask {
    protected String deadline;
    protected boolean formatted;
    protected LocalDate deadlineDate;

    /**
     * Constructor for DukeDeadline
     * If the date is formatted, it will be parsed into LocalDate.
     * @param taskName Name of the task.
     * @param deadline Deadline of the task(by).
     */
    public DukeDeadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
        try {
            this.deadlineDate = LocalDate.parse(deadline);
            this.formatted = true;
        } catch (Exception e) {
            this.formatted = false;
        }
    }

    /**
     * Prints the task in the format of:
     * "id. [Task Type] [Status Icon] Task Name (by: deadline)".
     * If the date can be parsed into LocalDate, it will be printed in the format of MMM d yyyy.
     * @param id the id of the task in the list.
     */
    @Override
    public void printTask(int id) {
        System.out.println((id +1) + ".[D] [" +
                this.getStatusIcon() + "] " + this.taskName + " (by: " +
                ((this.formatted) ? deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : deadline)
                + ")");
    }

    /**
     * Prints the task in the format of:
     * "[Task Type] [Status Icon] Task Name (by: deadline)".
     * If the date can be parsed into LocalDate, it will be printed in the format of MMM d yyyy.
     * @param id the id of the task in the list.
     */
    @Override
    public void printTask() {
        System.out.println("[D] [" +
                this.getStatusIcon() + "] " + this.taskName + " (by: " +
                ((this.formatted) ? deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : deadline)
                + ")");
    }

    /**
     * Returns the String in the format of:
     * "D | [Status Icon] | Task Name | deadline".
     * @return the string representation of the deadline.
     */
    @Override
    public String saveTask() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.taskName + " | " + this.deadline + '\n';
    }

    /**
     * @param date the date to be checked. (in the format of yyyy-mm-dd)
     * @return true if the deadline is before the date.
     * If the date is not formatted, it will return false.
     */
    @Override
    public boolean isDateMatch(LocalDate date) {
        if(!this.formatted) {
            return false;
        }
        return this.deadlineDate.isEqual(date);
    }
}