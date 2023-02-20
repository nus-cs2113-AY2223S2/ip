package Duke.DukeTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class DukeDeadline extends DukeTask {
    protected String deadline;
    protected boolean formatted;
    protected LocalDate deadlineDate;
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
    @Override
    public void printTask(int id) {
        System.out.println((id +1) + ".[D] [" +
                this.getStatusIcon() + "] " + this.taskName + " (by: " +
                ((this.formatted) ? deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : deadline)
                + ")");
    }
    @Override
    public void printTask() {
        System.out.println("[D] [" +
                this.getStatusIcon() + "] " + this.taskName + " (by: " +
                ((this.formatted) ? deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : deadline)
                + ")");
    }
    @Override
    public String saveTask() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.taskName + " | " + this.deadline + '\n';
    }
    @Override
    public boolean isDateMatch(LocalDate date) {
        if(!this.formatted) {
            return false;
        }
        return this.deadlineDate.isEqual(date);
    }
}