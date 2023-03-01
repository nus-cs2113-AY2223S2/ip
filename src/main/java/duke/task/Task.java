package duke.task;

/**
 * The Task class contains
 * details of a listed task
 */
public class Task {
    protected String description;
    public boolean isDone;

    /**
     * Constructor for a Task
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method returns the progress of a task
     * @return String
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This method returns the task in the form of a string
     * @return String
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
