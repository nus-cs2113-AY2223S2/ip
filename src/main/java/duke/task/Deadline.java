package duke.task;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;

import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;

/**
 * Task object with an additional deadline time parameter.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a deadline task with the given task name and deadline.
     *
     * @param taskName Name of the deadline task.
     * @param by       Time that the task should be completed by (given in dd/mm/yyyy tttt format, where tttt is time in
     *                 24-hour clock)
     */
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = LocalDateTime.parse(by);
    }

    /**
     * Converts the deadline task into its string representation which contains its task type, completion status, task
     * name, and the time it should be completed by. The time it should be completed by is given in the
     * ofLocalizedDateTime(FormatStyle.MEDIUM) format. For example, the deadline "12/02/2009 1000" is given as
     * "12 Feb 2009, 10:00:00 AM".
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(ofLocalizedDateTime(FormatStyle.MEDIUM)) + ")";
    }
}
