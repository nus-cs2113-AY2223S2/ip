package sage.tasktypes;

public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        if (super.isCompleted()) {
            return ".[T][X] " + super.getTaskName();
        } else {
            return ".[T][ ] " + super.getTaskName();
        }
    }

}
