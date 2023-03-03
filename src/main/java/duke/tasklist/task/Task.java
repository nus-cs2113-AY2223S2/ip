package duke.tasklist.task;

public abstract class Task {
    private String taskName;
    private boolean isCompleted;

    /**
     * Initialises an instance of Task.
     * Stores a task name, boolean isCompleted into the instance of Task.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Returns name of a task.
     *
     * @return Name of a task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns if a task is marked as completed or incomplete in string format for printing to CLI.
     *
     * @return X if task is marked as completed and whitespace if task is marked as incomplete.
     */
    public String getTaskStatus() {
        if (isCompleted) {
            return "X";
        }
        return " ";
    }

    /**
     * Returns if a task is marked as completed or incomplete in string format for saving to duke.txt.
     *
     * @return 1 if task is marked as completed and 0 if task is marked as incomplete.
     */
    public String getTaskStatusForFile() {
        if (isCompleted) {
            return "1";
        }
        return "0";
    }

    /**
     * Sets a task as completed.
     */
    public void setCompleted() {
        isCompleted = true;
    }

    /**
     * Sets a task as incomplete.
     */
    public void setIncomplete() {
        isCompleted = false;
    }

    /**
     * Returns task formatted as a string for printing to CLI.
     *
     * @return String format of task for printing to CLI.
     */
    @Override
    public String toString() {
        return "[" + getTaskStatus() + "] " + getTaskName();
    }

    /**
     * Returns task formatted as a string for saving to duke.txt.
     *
     * @return String format of task for saving to duke.txt.
     */
    public String toFile() {
        return "| " + getTaskStatusForFile() + " | " + getTaskName();

    }
}