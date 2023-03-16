package duke.tasks;

/**
 * Represents a basic task
 */
public class Task {
    protected String description;
    protected boolean isCompleted;

    /**
     * Constructor
     *
     * @param description task description
     * @param isCompleted task status
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the description string of the task
     *
     * @return description a string that shows the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the status of the task
     *
     * @return isCompleted a boolean value that represents the status of the task
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    public String showTask() {
        return getDescription();
    }

    public String showTaskLine() {
        String taskStatus;
        if (isCompleted) {
            taskStatus = "[T][√] ";
        } else {
            taskStatus = "[T][ ] ";
        }
        return taskStatus + showTask();
    }

    /**
     * This method returns a formatted String that represents the status of a task.
     * If the task is completed, the status is set to "1", otherwise it is set to "0".
     * Returned String is in the format of "T | taskStatus | [description] ".
     *
     * @return completeTaskLine a formatted String that represents the status of the task.
     */
    public String writeTaskLine() {
        String taskStatus;
        if (isCompleted) {
            taskStatus = "0";
        } else {
            taskStatus = "1";
        }
        String completeTaskLine = "T | " + taskStatus + " | " + showTask() + "\n";
        return completeTaskLine;
    }
}

