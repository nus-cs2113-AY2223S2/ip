package util;

/**
 * 
 * Represents a Todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the toString method of Task class to add a prefix [T] to the task
     * description.
     * 
     * @return Returns the formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
