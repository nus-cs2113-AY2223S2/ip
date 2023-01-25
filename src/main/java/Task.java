public class Task {
    protected String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * sets the current task to be marked as done
     *
     * @return the String of the marked task
     */
    public String setAsDone() {
        this.isDone = true;
        return this.task;
    }

    /**
     * sets the current task to be marked as not done
     *
     * @return the String of the marked task
     */
    public String setAsUndone() {
        this.isDone = false;
        return this.task;
    }

    /**
     * checks if the task is done, if done will return a tick,
     * however, if not done will return an empty space
     *
     * @return the String indicating the status of the task
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * get name of the current task
     *
     * @return String describing the task
     */
    public String getTask() {
        return task;
    }
}
