package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    static int totalNumberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        totalNumberOfTasks++;
        this.isDone = false;
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

    public String toString() {
      return "[T][" + this.getStatusIcon()+ "] " + description;
    }
}