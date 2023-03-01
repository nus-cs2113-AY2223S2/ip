package duke.task;

/**
 * The Todo class extends the Task class
 */
public class Todo extends Task {
    /**
     * Constructor for a Todo
     * @param description Task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * This method returns the todo in the form of a string
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
