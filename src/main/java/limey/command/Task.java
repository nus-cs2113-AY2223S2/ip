package limey.command;

public class Task {
    private String taskName;
    private boolean isDone = false;
    public static int numTasks = 0;

    public Task(String inLine) {
        if (inLine.contains("/")) {
            int firstSlash = inLine.indexOf("/");
            this.taskName = inLine.substring(0, firstSlash).trim();
        } else {
            this.taskName = inLine;
        }
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns identity of the current task including the following
     * - [X] or [ ] for marked or unmarked tasks
     * - task name
     *
     * @return tasks identity.
     */
    public String getTaskIdentity() {
        String taskIdentity;
        if (isDone) {
            taskIdentity = "[X] " + taskName;
        } else {
            taskIdentity = "[ ] " + taskName;
        }
        return taskIdentity;
    }

    /**
     * Sets the task to the given task name
     *
     * @param taskName the name of the task
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    /**
     * Sets the isDone boolean of the task to the respective input isDone
     *
     * @param isDone updated status of the task
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    /**
     * Returns the due date and time of the deadline in the format that the
     * list should print out the date and time
     */
    public String getDueDate() {
        return "";
    }
    /**
     * Returns the start date and time of the event in the format that the
     * list should print out the date and time
     */
    public String getFromDate() {
        return "";
    }
    /**
     * Returns the end date and time of the event in the format that the
     * list should print out the date and time
     */
    public String getToDate() {
        return "";
    }
    /**
     * Returns the due date of the deadline in the format that the user
     * initially input the /by date
     */
    public String getInDate() {
        return "";
    }
    /**
     * Returns the start date and time of the event in the format that the
     * list should print out the date and time
     */
    public String getInFromDate() {
        return "";
    }
    /**
     * Returns the end date of the event in the format that the user
     * initially input the /to date
     */
    public String getInToDate() {
        return "";
    }
}

