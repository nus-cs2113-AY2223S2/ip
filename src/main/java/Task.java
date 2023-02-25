public class Task {
    private String taskName;
    private Boolean isDone;

    /**
     * Constructor for Task Object.
     */
    public Task() {
        taskName = "";
        isDone = false;
    }

    /**
     * Constructor for Task Object using taskName.
     *
     * @param taskName name of new task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    /**
     * Getter function for task name.
     *
     * @return task name of current task object.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Getter function for isDone boolean variable of task.
     *
     * @return True if task is done, False otherwise.
     */
    public Boolean getisDone() {
        return isDone;
    }

    /**
     * Setter function to set task name.
     *
     * @param taskName new task name for task.
     */
    public void setTaskName (String taskName) {
        this.taskName = taskName;
    }

    /**
     * Setter function for isDone boolean variable of task.
     *
     * @param isDone boolean input for isDone of task.
     */
    public void setisDone(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Overides toString() of Object class.
     *
     * @return String indication of whether task is done + task name.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}


