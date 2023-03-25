package duke.classes;

/**
 * Represents a todo task.
 * Inherits from Task class.
 */
public class Todo extends Task{

    /**
     * Constructor for Todo class.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {

        super(description);
    }

    /**
     * Returns a formatted string representation of the task.
     * Overrides the toString() method in the Task class.
     *
     * @return Formatted string representation of the task.
     */
    @Override
    public String toString() {

        return "[T][" + getStatusIcon() + "] " + super.toString();
    }
}
