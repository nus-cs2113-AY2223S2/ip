package duke.tasks;

/**
 * Deadline class that keeps track of the task object with an additional deadline parameter.
 */
public class Deadline extends Task {
    /**
     * The deadline in which the task should be completed by.
     */
    protected String by;

    /**
     * Constructs a deadline task with the given description and deadline.
     *
     * @param description Description of the deadline task.
     * @param by Time that the task should be completed by.
     */
    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    /**
     * Converts the deadline task into its string representation which contains
     * information such as the task type, completion status, description and due date.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts the deadline task into a format to be saved in the database.
     *
     * @return String representation of the deadline task meant for saving into the database.
     */
    @Override
    public String taskInformation() {
        return String.format("%s , %s , %s", "deadline", super.taskInformation(), this.by);
    }
}