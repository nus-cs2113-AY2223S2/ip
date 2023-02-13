package max.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return "[" + getTaskLabel() + "]" + super.getDescription();
    }

    @Override
    public String getTaskLabel() {
        return "T";
    }
}
