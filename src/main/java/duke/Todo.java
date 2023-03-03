package duke;

/**
 * Todo task subclass
 */
public class Todo extends Task {
    /**
     * Constructor for a new Todo
     * @param desc the description of the Todo
     */
    public Todo(String desc) {
        super(desc);
        this.type = TaskType.TODO;
    }
}

