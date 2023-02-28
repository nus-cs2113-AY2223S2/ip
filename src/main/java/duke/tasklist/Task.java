package duke.tasklist;

/**
 * Represents a task to be done.
 */
public class Task {
    /**
     * The description of this task.
     */
    protected String description;

    /**
     * The type of this task.
     */
    protected String type;

    /**
     * The completion status of this task.
     * Complete is represented by isDone == true,
     * while incomplete is represented by isDone == false.
     */
    protected boolean isDone;
    private static String line = "__________________________________________________________";


    /**
     * Creates a task with the given description.
     * Default status is incomplete, and has no type.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "0";
    }

    /**
     * Gets the icon to represent the status of the task.
     * @return the status of completion.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the type of this task.
     * @return the type of this task
     */
    public String getType() {
        return (type);
    }

    /**
     * Returns the string representation of this task.
     * @return the type, status and task description in a string.
     */
    public String toString() {
        return ("[" + getType() + "][" + getStatusIcon() + "] " + description);
    }

    /**
     * Updates the status of this task after reading from the txt file.
     * @param status the status of this task
     *               as stored in the list previously.
     */
    public void addIsDone(String status) {
        if(status.equals("X")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * Marks a task as completed or unmarks a task as incomplete.
     * Updates the status of this task.
     * @param action User command to be executed.
     *               Should include mark or unmark.
     */
    public void markAsDone(String action) {
        if (action.equals("unmark")) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }
}

