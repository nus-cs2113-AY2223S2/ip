package duke.task;

public class Todo extends Task {
    protected final String taskName;
    public Todo(String description) {
        super(description);
        this.taskName = description;
    }

    public String returnCommand() {
        return "todo " + taskName;
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
