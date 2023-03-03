package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The <code>Deadline</code> class contains key variables that
 * is present in <code>Task</code> class with the addition of
 * a <code>by</code> variable storing the <code>String</code> of the due date
 * and a <code>byDate</code> storing the date format of the
 * due date.
 * <p></p>
 * There are also methods that could modify and retrieve the
 * task variables present in the class.
 */
public class Deadline extends Task{
    protected String by;
    protected LocalDate byDate;

    /**
     * Class constructor with <code>description</code> and
     * <code>doneBy</code> as parameters for initialization.
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
    public String getBy() {
        return by;
    }
    @Override
    public String toString() {
        return '[' + super.getType() + "]" + super.toString() + "(by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ')';
    }

}
