package duke.tasklist.task;

public class Todo extends Task {
    private static final String TODO_ICON = "T";

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "["+ TODO_ICON + "]" + super.toString();
    }

    @Override
    public String toFile() {
        return TODO_ICON + " " + super.toFile();
    }
}
