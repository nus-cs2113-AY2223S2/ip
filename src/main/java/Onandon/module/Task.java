package Onandon.module;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

<<<<<<< HEAD
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getDescription() {
        return description;
    }
    public void markAsDone() {
        isDone = true;
    }
    public void markAsUnDone() {
        isDone = false;
    }
=======
    public String getStatusIcon() { return (isDone ? "X" : " "); } // mark done task with X
    public String getDescription() { return description; }
    public void markAsDone() { isDone = true; }
    public void markAsUnDone() { isDone = false; }
    public String getBy() { return ""; }
    public String getTo() { return ""; }
    public String getFrom() { return ""; }
>>>>>>> branch-Level-7
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]";
    }
}