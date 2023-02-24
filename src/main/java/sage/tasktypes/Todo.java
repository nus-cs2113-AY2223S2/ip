package sage.tasktypes;

/**
 * A class that represents a todo task
 */
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
