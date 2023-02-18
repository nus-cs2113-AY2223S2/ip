/**
 * Represents a task which only has a description.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type of task.
     * @return "T" representing Todo task
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Returns a String representation of the current Todo task.
     * @return Current task's type and description in a string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
