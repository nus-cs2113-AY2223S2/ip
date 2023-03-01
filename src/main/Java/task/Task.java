package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        setDescription(description);
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getIsDone() {
        return (isDone ? "1" : "0");
    }

    public String stringRepresentation() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
