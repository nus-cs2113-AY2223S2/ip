package duke;

/**
 * The class of general task, which has three inheritors, event, todo, and deadline.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsNotDone() {
        this.isDone = false;
    }
    public String toString() {
        String done = " ";
        if (this.isDone) {
            done = "X";
        }
        return "[" + done + "] " + description ;
    }
    public String toStorage() {
        String done = " ";
        if (this.isDone) {
            done = "X";
        }
        return "[" + done + "] " + description ;
    }
}
