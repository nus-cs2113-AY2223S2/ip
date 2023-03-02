package duke;

public class Todo extends Task {

    /**
     * Creates a new Todo object
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo object
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns the string representation of the Todo object to be saved in the data
     */
    @Override
    public String saveTask() {
        return ("T" + "//" + checkCompletion() + "//" + getDescription() + "\n");
    }

}
