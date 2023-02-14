package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    private static int counter = 0;

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

    public static int getCounter() {
        return counter;
    }

    public void increaseCounter() {
        counter++;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatus() {
        return (isDone() ? "X" : " ");
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return description;
    }
}
