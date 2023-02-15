package tasks;

/**
 * A Todo object is simply a Task object with only a description.
 */
public class Todo extends Task{
    /**
     * @param description The description of the task to be done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
        setFormattedDescription(description);
    }
}
