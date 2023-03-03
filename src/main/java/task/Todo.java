package task;

/**
 * Represent a Todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Get the character representing the type "Todo".
     *
     * @return "T"
     */
    public String getTaskType() {
        return "T";
    }
}
