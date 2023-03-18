package Duke.Tasks;

/**
 * Represents a task consisting of the task description and whether the task is done.
 * Deadline, Todo, and Event all inherit this class.
 */
public class Task {
    protected String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return ("[" + getStatusIcon() + "]" + description);
    }


}
