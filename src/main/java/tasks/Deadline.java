package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline object is a Task object that has a due date.
 */
public class Deadline extends Task{
    private LocalDate by;

    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setBy(String by) {
        this.by = LocalDate.parse(by.trim());
    }

    /** Upon creating the Deadline, the by date will be concatenated into the displayed description.
     * @param description The description of this task.
     * @param by The date this task has to be done.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        setBy(by);
        setFormattedDescription(description + "(by: " + getBy() + ')');
    }
}
