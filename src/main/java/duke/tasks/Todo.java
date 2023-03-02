package duke.tasks;

public class Todo extends Task {
    private boolean isDone;

    public Todo(String description, boolean isDone) {
        super(description,isDone);
    }

    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
