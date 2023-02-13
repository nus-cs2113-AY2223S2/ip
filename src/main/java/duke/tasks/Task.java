package duke.tasks;

public class Task {
    private String description;
    private boolean isDone;

    private String type;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTaskName() {
        return this.description;
    }

    public String getType() {
        return type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}