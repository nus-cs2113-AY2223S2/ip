package duke.task;

/**
 * Template for a task object.
 */
public class Task {
    public String description;
    protected boolean isDone;
    String LINE = "────────────────────────────────────────────────────────────────────────\n";
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    @Override
    public String toString() {
        return getTaskStatusBrackets() + description;
    }

    /**
     * Sets the task status to "done" by set the isDone boolean to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the task status to "undone" by set the isDone boolean to false.
     */
    public void setUndone() {
        this.isDone = false;
    }

    public void printSetDoneMessage() {
        setDone();
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as DONE:");
        System.out.println("  " + this.toString());
        System.out.println(LINE);
    }

    public void printSetUndoneMessage() {
        setUndone();
        System.out.println(LINE);
        System.out.println("OK! I've marked this task as NOT DONE YET:");
        System.out.println("  " + this.toString());
        System.out.println(LINE);
    }

    /**
     * Returns the status icon for a task object as a string.
     * Icon <code>X</code> represents done, a blank icon represents not done.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getTaskStatusBrackets() {
        return ("[" + getStatusIcon() + "] ");
    }
}
