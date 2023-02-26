package DukeFunctions;

/**
 * Represents a task on the TaskList.
 */
public class Todo {

    private String description;
    String type;
    private boolean isDone;

    /**
     * Constructor for the Todo class.
     *
     * @param contents contents of the task.
     */
    public Todo(String contents) {
        this.description = contents;
        this.isDone = false;
        this.type = "T";
    }

    /**
     * Gets the status of the task.
     *
     * @return string status of the task (either " " or "X").
     */
    public String getIsDone() {
        String status = " ";

        if (this.isDone == true) {
            status = "X";
        }

        return status;
    }

    /**
     * Gets the type of the task.
     *
     * @return string type of the task.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return string description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the description of the task.
     *
     * @return string description of the task.
     */
    @Override
    public String toString() {
        return description;
    }
}