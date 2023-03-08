package duke.task;

/**
 * Task object with no additional parameters.
 */
public class ToDo extends Task {
    /**
     * Constructs a todo task with the given task name.
     *
     * @param taskName Name of the todo task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Converts the todo task into its string representation, which contains its task type, completion status, and task
     * name.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
