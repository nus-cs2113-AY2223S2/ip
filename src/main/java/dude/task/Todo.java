package dude.task;

public class Todo extends Task {
    /**
     * Constructor for Todo class.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the task to be saved.
     *
     * @return String representation of the task to be saved.
     */
    @Override
    public String toSave(){
        return getStatus() + "=" + "todo " + super.getDescription();
    }
}
