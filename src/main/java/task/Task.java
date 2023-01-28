package task;

public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
