public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + description;
    }
    public String getUpdate() {
        return (description);
    }
}
