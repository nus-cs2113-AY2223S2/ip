package duke;
public class Task {
    public String description;
    public String by;
    public String from;
    public String to;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
