package duke.tasks;


public abstract class Task implements java.io.Serializable {

    private boolean isComplete;
    private String taskName;

    public Task() {
        isComplete = false;
        taskName = null;
    }

    public Task(String taskName) {
        isComplete = false;
        this.taskName = taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public abstract String taskTypeBoxFormat();

    /**
     * Returns the formatted box in <code>String</code> format.
     * Formats the box based on whether the task is already marked as completed.
     *
     * @return The formatted box.
     */
    public String markedBoxFormat() {
        if (isComplete) {
            return "[X]";
        }
        return "[ ]";


    }


}
