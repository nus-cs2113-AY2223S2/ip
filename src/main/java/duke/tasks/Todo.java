package duke.tasks;

/**
 * Represents a todo Task
 */
public class Todo extends Task {

    /**
     * Constructor for todo task represented with specified type "T"
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
        this.type = "[T]";
    }

    /**
     * Output the todo task and its details
     *
     * @return the type, status and description of current todo task
     */
    @Override
    public String toString() {
        return super.toString() + getType() + getStatusIcon() + getDescription();
    }

}


