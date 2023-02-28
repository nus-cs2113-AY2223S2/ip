import duke.Task;

/**
 * Represents a todo task.
 * It is a child of the Task class.
 */
public class Todo extends Task{
    /**
     * Creates a todo task with the given description.
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
