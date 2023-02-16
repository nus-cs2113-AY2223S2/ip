package Tasks;

public class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        return "[" + this.getStatus() + "] " + this.getDescription();
    }
}
