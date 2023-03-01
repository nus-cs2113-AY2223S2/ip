package duke.task;

public class Task {
    protected String taskDescription;
    protected boolean isDone;
    protected String command;

    /**
     * Gets description of the task.
     *
     * @return Returns the description of task.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets description of the task.
     *
     * @param command Description of the task.
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Constructs the description of task and set done to false.
     *
     * @param taskDescription Description of task.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Gets "X" if the task is done, else an empty character.
     *
     * @return If task is done "X", else " ".
     */
    public String getStatusIcon() {
        // mark task as done with X
        return (isDone ? "X" : " ");
    }

    // mark and un-mark done
    // true -> done; false -> not done

    /**
     * Sets the done status of a task
     *
     * @param done The done status of a task
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * @return If task is done true, else false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return Description of task.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Sets description of the task.
     *
     * @param taskDescription Description of the task to be set.
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Makes a format of string to be printed.
     *
     * @return String of deadline with format.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskDescription;
    }

    /**
     * Gets string to save into .txt file.
     *
     * @return String to be saved.
     */
    public String getSave() {
        return (isDone() ? "1 " : "0 ") + command + " " +  System.lineSeparator();
    }
}
