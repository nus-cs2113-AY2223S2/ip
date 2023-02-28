package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This object represents a task object of deadline type. A deadline type task object contains a non-<code>null</code>
 * <code>byDate</code> and <code>fromDate</code>.
 * If <code>byDate</code> and <code>fromDate</code></code> is in the format of
 * <code>LocalDate</code>, <code>localByDate</code> and <code>localFromDate</code> would be non-<code>null</code>.
 */
public class Event extends Task {
    protected String byDate;
    protected String fromDate;

    /**
     * Initializes a task object of type event.
     * Sets the task object's type to E.
     *
     * @param task The contents of the task object.
     * @param isDone The initialize status marked/unmarked of the task object.
     * @param fromDate The <code>fromDate</code> in <code>String</code> format.
     * @param toDate The <code>byDate</code> in <code>String</code> format.
     * @param localByDate The <code>byDate</code> in <code>LocalDate</code> format given that it is entered in an
     * eligible format.
     * @param localFromDate The <code>fromDate</code> in <code>LocalDate</code> format given that it is entered in an
     * eligible format.
     */
    public Event(String task, boolean isDone, String fromDate, String toDate, LocalDate localByDate
            , LocalDate localFromDate) {
        super(task, isDone);
        this.type = "E";
        this.byDate = toDate;
        this.fromDate = fromDate;
        this.localByDate = localByDate;
        this.localFromDate = localFromDate;
    }

    /**
     * Override the template task getTask with additional attribute <code>byDate</code> and <code>fromDate</code> and
     * prepares it for storage in datafile.
     *
     * @return Returns all attributes of the task object demarcated with <code>%</code>.
     */
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

    /**
     * Overrides the template task toString with additional attribute <code>byDate</code> and <code>fromDate</code> and
     * returns it as <code>String</code> representation of the task object.
     * <code>byDate</code> and <code>fromDate</code> is instead returned as <code>LocalDate</code> if the format
     * is eligible.
     *
     * @return Return the task object's <code>String</code> representation with all its attributes.
     */
    @Override
    public String toString() {
        if (localFromDate == null && localByDate == null) {
            return super.toString() + " (from: " + fromDate + " to: " + byDate + ")";
        } else if (localFromDate == null) {
            return super.toString() + " (from: " + fromDate
                    + " to: " + localByDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else if (localByDate == null) {
            return super.toString() + " (from: " + localFromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " to: " + byDate + ")";
        }
        return super.toString() + " (from: " + localFromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + localByDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
