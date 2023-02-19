package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String byDate;
    protected String fromDate;

    public Event(String task, boolean isDone, String fromDate, String toDate, LocalDate localByDate
            , LocalDate localFromDate) {
        super(task, isDone);
        this.type = "E";
        this.byDate = toDate;
        this.fromDate = fromDate;
        this.localByDate = localByDate;
        this.localFromDate = localFromDate;
    }

    @Override
    public String getTask() {
        return super.getTask() + " % " + fromDate + " % " + byDate;
    }

    public LocalDate getLocalByDate() {
        return localByDate;
    }

    public LocalDate getLocalFromDate() {
        return localFromDate;
    }

    @Override
    public String toString() {
        if (localFromDate == null && localByDate == null) {
            return super.toString() + " (from: " + fromDate + " to: " + byDate + ")";
        } else if (localFromDate == null) {
            return super.toString() + " (from: " + fromDate
                    + " to: " + localByDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return super.toString() + " (from: " + localFromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + byDate + ")";
    }
}
