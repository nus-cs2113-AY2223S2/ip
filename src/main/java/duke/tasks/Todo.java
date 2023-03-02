package duke.tasks;

/**
 * Class creates a Todo type task.
 */
public class Todo extends Task {
    private boolean isDone;

    /**
     * Creates a Todo object and initializes isDone.
     *
     * @param description Description of deadline user wants to add.
     * @param isDone Tells if this deadline has been completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the task type of todo.
     *
     * @return Task type.
     */
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
