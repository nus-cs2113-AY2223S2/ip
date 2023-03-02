package duke;

/**
 * The class represent a todo item in the task list.
 */
public class Todo extends Task {
    protected boolean isDone;

    public Todo(String description) {
        super(description);
        isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
    public String toStorage() {
        return "[T]" + super.toStorage();
    }
}
