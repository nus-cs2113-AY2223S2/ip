//package Duke.java;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {

        return (isDone ? "V" : "X"); //return tick or X symbols
    }

    public void markAsDone() {

        this.isDone = true;
    }

    public void markAsNotDone() {

        this.isDone = false;
    }

    public String toString() {

        return String.format("[%s] %s", getStatusIcon(), description);
    }
}