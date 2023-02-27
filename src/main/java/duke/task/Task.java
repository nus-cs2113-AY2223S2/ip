package duke.task;

/**
 * The <code>Task</code> object represents the task that the user adds.
 * It contains the description of the task, whether the task is done and the type of the task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * A class constructor that sets the description of the task,
     * the default value of false for whether it is done, and
     * the task type as the default value of TODO.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = TaskType.TODO;
    }

    /**
     * Sets the task as done or undone.
     *
     * @param isDone The boolean value of whether the task is done or undone.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Checks and returns a string to indicate whether the task is done.
     *
     * @return A String "X" if the task is done and " " if the task is undone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Checks the task type and returns a specific String that corresponds to the task type.
     *
     * @return A String indicating the task type.
     */
    public String getType() {
        switch (this.taskType) {
        case TODO:
            return "T";
        case DEADLINE:
            return "D";
        case EVENT:
            return "E";
        default:
            return null;
        }
    }

    /**
     * Formats the task into a specific String.
     *
     * @return A formatted String representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "][" + this.getStatusIcon() + "] " + description;
    }
}
