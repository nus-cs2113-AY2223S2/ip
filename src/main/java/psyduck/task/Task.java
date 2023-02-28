package psyduck.task;

public class Task {
    protected String description;
    protected boolean isDone;

    protected String type;

    public String getType() {
        return type;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
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
        this.isDone = false;
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
