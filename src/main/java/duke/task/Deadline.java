package duke.task;

/**
 * Represents a deadline Task
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for a deadline task
     *
     * @param description the description of the deadline task
     * @param taskType the type of task
     * @param by the timing which the task is due
     */
    public Deadline(String description, String taskType, String by) {
        super(description, taskType);
        this.by = by;
    }

    /**
     * Output the deadline task and its details
     *
     * @return the type, status, description and timing which the task is due
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    /**
     * Represents the details of the deadline task to be saved on the text file
     *
     * @return the type, status, description and timing which the task is due
     */
    @Override
    public String textToSave() {
        return "D | " + (super.isDone ? 1 : 0) + " | " + super.description + " | " + by;
    }
}