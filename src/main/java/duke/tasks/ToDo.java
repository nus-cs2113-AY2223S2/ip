package duke.tasks;

/**
 * This class inherits from Task class.
 * ToDos only contain a description.
 */
public class ToDo extends Task {
    /**
     * Creates a todo object.
     * @param description
     */
    public ToDo(String description) {
        super(description, "T");
    }
}
