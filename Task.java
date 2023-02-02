public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // tick if done, cross if undone
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIsDone(boolean done) {
        this.isDone = done;
    }
    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}