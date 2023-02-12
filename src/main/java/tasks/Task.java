package tasks;

public class Task {
    private String description;
    private boolean isDone;

    private String command;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getDescription() {
        return (this.description);
    }

    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.getDescription());
    }

    public String getCommand() {
        return this.command;
    }
}