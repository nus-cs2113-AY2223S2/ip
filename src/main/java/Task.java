/**
 * Individual tasks.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // To be used to list out the task.
    public String getTaskListing() {
        return (this.getStatusIcon() + " " + this.description);
    }

    // return a string to indicate done or not done.
    private String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    // use "mark" to mark done
    public void markAsDone() {
        this.isDone = true;
    }
    // use "unmark" to mark a task not done
    public void markAsUndone() {
        this.isDone = false;
    }
}
