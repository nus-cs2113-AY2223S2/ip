package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, 'T');
    }

    @Override
    public String getListDescription() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + getDescription();
    }

}
