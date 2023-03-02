package duke;

public class Task {

    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskType() {
        return taskType;
    }
    
    public String toString() {
        String[] command =  description.split("/ | :");
        return "[" + getStatusIcon() + "] "  + command[0];
    }

}
