package duke;

/**
 * Represents a task. A <code>Task</code> object represents various possible
 * task types such as Event, Deadline or Todo. User may specify their desired
 * task type via the respective commands e.g.,
 * <code>/event do homework /start 01-08-2023 /end 05-08-2023</code> or
 * <code>/todo meet John</code>
 */
public class Task {
    public String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public boolean getDoneStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String formattedString() {
        return null;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
