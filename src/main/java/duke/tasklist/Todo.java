package duke.tasklist;

/**
 * Represents a type of task called Todo.
 */
public class Todo extends Task{

    /**
     * Creates a todo task with the given description.
     * @param description The description of the todo task
     */
    public Todo(String description) {
        super(description);
        this.type = "T";
    }
}
