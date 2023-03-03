package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
    public String getDescription() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String getPureDescription() {
        return this.description;
    }
}
