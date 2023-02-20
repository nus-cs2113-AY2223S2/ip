package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This object represents a task object of deadline type. A deadline type task object contains a non-<code>null</code>
 * <code>byDate</code>.
 * If <code>byDate</code> is in the format of <code>LocalDate</code>, <code>localByDate</code> would be
 * non-<code>null</code>.
 */
public class Deadline extends Task {
    protected String byDate;

    /**
     * Initializes a task object of type event.
     * Sets the task object's type to D.
     *
     * @param task The contents of the task object.
     * @param isDone The initialize status marked/unmarked of the task object.
     * @param localByDate The <code>byDate</code> in <code>LocalDate</code> format given that it is entered in an
     * eligible format.
     */
    public Deadline(String task, boolean isDone, String byDate, LocalDate localByDate) {
        super(task, isDone);
        this.type = "D";
        this.byDate = byDate;
        this.localByDate = localByDate;
    }

    /**
     * Override the template task getTask with additional attribute <code>byDate</code> and prepares it for
     * storage in datafile.
     *
     * @return Returns all attributes of the task object demarcated with <code>%</code>.
     */
    @Override
    public String getTask() {
        return super.getTask() + " % " + byDate;
    }

    /**
     * Overrides the template task toString with additional attribute <code>byDate</code> and returns it as a
     * <code>String</code> representation of the task object.
     * <code>byDate</code> is instead returned as <code>LocalDate</code> if the format is eligible.
     *
     * @return Return the task object's <code>String</code> representation with all its attributes.
     */
    @Override
    public String toString() {
        if (localByDate == null) {
            return super.toString() + " (by: " + byDate + ")";
        }
        return super.toString() + " (by: " + localByDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
