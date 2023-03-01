package task;

/**
 * Model a class to handle todo, inherit from Task class.
 */
public class Todo extends Task{
    /**
     * Build constructor for Todo class.
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Method to override toString().
     * @return the string representation of the class.
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription();
    }
}
