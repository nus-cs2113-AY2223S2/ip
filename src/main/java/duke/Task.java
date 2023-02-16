package duke;

/**
 * Represents a Task that contains attributes such as the details of the task and
 * whether the task has been completed.
 */
public class Task {
    protected String details;
    protected boolean isDone;

    public Task(String details) {
        this.details = details;
        this.isDone = false;
    }

    public String getDetails() {
        return this.details;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.details;
    }


}
