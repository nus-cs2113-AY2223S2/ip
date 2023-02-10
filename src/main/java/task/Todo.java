package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, 'T');
    }

    public Todo(String description, boolean isDone) {
        super(description, 'T', isDone);
    }

    @Override
    public String getListDescription() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}
