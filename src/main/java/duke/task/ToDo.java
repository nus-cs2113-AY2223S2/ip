package duke.task;

/**
 * Represents todo object that it a type of task
 */
public class ToDo extends Task {
    /**
     * Constructor for todo task object, with specified type "T"
     *
     * @param description the description of the current todo
     */
    public ToDo(String description) {
        super(description);
        this.type = "[T]";
    }

    /**
     * Prints the todo object elements
     *
     * @return the type, status and description of current todo object
     */
    @Override
    public String toString() {
        return super.toString() + getType() + getStatusIcon() + getDescription();
    }
}
