package tasks;

/**
 * A <code>ToDo</code> class that represents a ToDo task.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo object with the given description.
     *
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the type of the task: <code>T</code> for ToDo.
     *
     * @return the type of the task, which is <code>T</code>
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return a string representation of the ToDo task
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + super.toString();
    }
}
