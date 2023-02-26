package limey.command;

public class Task {
    private String taskName;
    private boolean isDone = false;
    public static int numTasks = 0;

    public Task(String inLine) {
        //int firstSpace = inLine.indexOf(" ");
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

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDueDate() {
        return "";
    }

    public String getFromDate() {
        return "";
    }

    public String getToDate() {
        return "";
    }

    public String getInDate() {
        return "";
    }

    public String getInFromDate() {
        return "";
    }

    public String getInToDate() {
        return "";
    }
}

