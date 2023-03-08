package alltasks;

/**
 * This Task class represents all tasks input by users such as
 * todo, deadline and event tasks in a list.
 * Coffee Bot keeps track of these tasks in a list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task class from the input command given.
     *
     * @param description description of input command.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns letter "X" or " " to indicate if a task is done or not done.
     *
     * @return X If isDone is true, return " " If isDone is false.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getInfo() {
        return "";
    }

    /**
     * Returns the description of the input command.
     *
     * @return description description of input command.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets this.isDone to true to indicate that a task is marked done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets this.isDone to false to indicate that a task is marked not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status of completion of the task item
     * and the description of the input command.
     *
     * @return getStatusIcon() status of completion of task item.
     * @return description description of the input command.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
