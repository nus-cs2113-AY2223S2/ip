package duke.task;

public class Todo extends Task {
    protected String task;

    public Todo(String description) {
        super(description);
        task = description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + task;
    }

}
