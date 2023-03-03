package inu.task;

/**
 * Represents a to-do.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String encodeTaskToString() {
        return String.format("T//%s//%s", getStatusIcon(), getDescription());
    }
}
