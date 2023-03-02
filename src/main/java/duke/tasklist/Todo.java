package duke.tasklist;

/**
 * Represents a type of task called To-do.
 */
public class Todo extends Task{

    /**
     * Creates a to-do task with the given description.
     * @param description The description of the to-do task
     */
    public Todo(String description) {
        super(description);
        this.type = "T";
    }
}
