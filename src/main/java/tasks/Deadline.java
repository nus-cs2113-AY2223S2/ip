package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String by;
    protected LocalDate byDate;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Class constructor with <code>description</code> and
     * <code>doneby</code> as parameters for initialization.
     *
     * @param description the description of the task.
     * @param doneBy the due date of the task.
     * @throws DateTimeException if date is given in wrong format.
     */
    public Deadline(String description, String doneBy) throws DateTimeException {
        super(description, "D");
        by = doneBy;
        byDate = LocalDate.parse(doneBy);
    }
    @Override
    public String toString() {
        return '[' + super.getType() + "]" + super.toString() + "(by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ')';
    }

}
