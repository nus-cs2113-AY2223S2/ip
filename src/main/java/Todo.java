/**
 * Represents a special type of task that has no deadline.
 */
public class Todo extends Task {
    public Todo(String name, boolean isCompleted) {
        super(name, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
