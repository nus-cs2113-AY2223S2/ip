package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String byDate;
    protected LocalDate localByDate;

    public Deadline(String task, boolean isDone, String byDate, LocalDate localByDate) {
        super(task, isDone);
        this.type = "D";
        this.byDate = byDate;
        this.localByDate = localByDate;
    }

    @Override
    public String getTask() {
        return super.getTask() + " % " + byDate;
    }

    @Override
    public String toString() {
        if (localByDate == null) {
            return super.toString() + " (by: " + byDate + ")";
        }
        return super.toString() + " (by: " + localByDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
