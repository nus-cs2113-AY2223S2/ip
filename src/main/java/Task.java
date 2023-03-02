public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
    }

    public String toString() {
        return getStatusIcon() + description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }
}