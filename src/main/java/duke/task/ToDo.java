package duke.task;

public class ToDo extends Task {

    /**
     * Create a new "todo" task.
     *
     * @param description The description/name of the "todo" task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string for output printing, containing information about the task.
     *
     * @return String containing the category label of the task, completion status, description of task
     * and dates (if any).
     */
    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.getDescription();
    }
}
