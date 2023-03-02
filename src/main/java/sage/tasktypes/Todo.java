package sage.tasktypes;

/**
 * An inherited class from Task that represents Todo.
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
