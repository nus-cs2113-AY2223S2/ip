package duke.task;

public class Todo extends Task {
    protected static final String TODO_ICON = "T";

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return OPEN_SQUARE_BRACKET + TODO_ICON + CLOSE_SQUARE_BRACKET + super.toString();
    }

    @Override
    public String toFile() {
        return TODO_ICON + " " + super.toFile();
    }
}
