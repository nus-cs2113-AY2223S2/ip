package task;

public abstract class Task {
    protected String description;

    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatusNum() {
        return (isDone ? "1" : "0");
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String getTaskType();

    public String getDataSummary() {
        return getTaskType() + " | " + getStatusNum() + " | " + getDescription();
    }

    public String getSummary() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + getDescription();
    }
}