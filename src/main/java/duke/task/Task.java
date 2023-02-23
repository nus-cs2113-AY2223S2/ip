package duke.task;

public class Task {
    protected String taskDescription;
    protected boolean isDone;
    protected String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String getStatusIcon() {
        // mark task as done with X
        return (isDone ? "X" : " ");
    }

    // mark and un-mark done
    // true -> done; false -> not done
    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + taskDescription;
    }
    public String getSave() {
        return (isDone() ? "1 " : "0 ") + command + " " +  System.lineSeparator();
    }
}
