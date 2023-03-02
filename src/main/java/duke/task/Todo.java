package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String taskOutput = super.toString();
        return String.format("[T]%s", taskOutput);
    }
}
