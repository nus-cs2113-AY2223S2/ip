public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        return " [" + (isDone ? "X" : " ") + "] " + description;
    }
}
