package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static String printPattern = "MMM dd yyyy, hh:mma";
    public static String storePattern = "yyyy-mm-ddTHH:mm";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getType() {
        return "task";
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatus() {
        return (isDone() ? "X" : " ");
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return description;
    }
}
