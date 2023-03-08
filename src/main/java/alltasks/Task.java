package alltasks;

public class Task {
    protected String description; // instance variable
    protected boolean isDone;

    public Task(String description) { // constructor
        this.description = description;
        this.isDone = false;
    }

    // getter
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // methods mark done task with X
    }

    //getter
    public String getInfo() {
        return "";
    }

    //getter
    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
