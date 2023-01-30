public class Task {
    protected String description;
    protected boolean isDone;

    public boolean isDone() {
        return isDone;
    }
    public String getDescription() {
        return description;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String status;
        if (isDone) {
            status = "[X] ";
        } else {
            status = "[ ] ";
        }
        return status + description;
    }

}
