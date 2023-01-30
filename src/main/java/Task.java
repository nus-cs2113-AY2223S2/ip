public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone() {
        isDone = true;
    }

    public void setUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[ ][X] " + description;
        } else {
            return "[ ][ ] " + description;
        }
    }
}