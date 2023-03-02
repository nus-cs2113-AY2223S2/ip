package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String toSaveString(String... taskParameters) {
        return super.toSaveString("T", isDone ? "1" : "0", description);
    }
}
