package sage.tasktypes;

public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        if (super.isCompleted()) {
            return "[T][X] " + super.getTaskDetails();
        } else {
            return "[T][ ] " + super.getTaskDetails();
        }
    }

}
