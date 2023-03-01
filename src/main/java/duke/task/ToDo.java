package duke.task;

/**
 * Basic version of a Task object with no additional parameters.
 */
public class ToDo extends Task {
    /**
     * Constructs a todo task with the given description.
     *
     * @param description Description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the todo task into its string representation,
     * which contains its task type, completion status, and description.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return " [T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return super.toSaveString("T", isDone ? "1" : "0", description);
    }
}
