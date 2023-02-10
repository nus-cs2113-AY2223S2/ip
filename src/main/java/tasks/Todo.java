package tasks;

import tasks.Task;

public class Todo extends Task {
    public String by;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "]" + super.description.toString();
    }
}
