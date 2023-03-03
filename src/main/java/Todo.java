/**
 * Represents a task that the user wishes to complete.
 */

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getTaskIcon() {
        return "T";
    }
    /**
     * Returns the task type and shows whether the task has been completed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
