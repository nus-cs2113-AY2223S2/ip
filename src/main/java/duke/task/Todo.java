package duke.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    protected String task;

    /**
     * Constructs a new Todo task.
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
        task = description;
    }

    @Override
    /**
     * Returns a string for the Todo task.
     * @return String indicating the task type and description of the todo
     */
    public String toString() {
        return "[T]" + super.toString() + task;
    }

}
