package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends ToDo {

    protected LocalDate deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param taskName Task description.
     * @param deadline Deadline of task.
     */
    public Deadline(String taskName, LocalDate deadline) {
        super(taskName);
        super.type = "[D]";
        this.deadline = deadline;
    }

    /**
     * Formats the deadline to a more readable format.
     *
     * @return Formatted deadline.
     */
    public String formatDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the deadline of the task.
     *
     * @return Deadline of task.
     */
    public String getDeadline() {
        return this.deadline.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return checkBoxOutput() + this.taskName + " (by: " + this.formatDeadline() + ")";
    }
}
