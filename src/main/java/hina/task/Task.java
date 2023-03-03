package hina.task;

/**
 * Represents a task on a to-do list that can be set as done or undone.
 *
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Returns done status of the <code>Task</code>.
     *
     * @return done status.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the done status of the <code>Task</code>.
     *
     * @param done status to set the done status to.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Class constructor specifying this <code>Task</code>'s <code>description</code>.
     *
     * @param description name of this <code>Task</code>.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns the <code>description</code> of this <code>Task</code>.
     *
     * @return <code>description</code> of this <code>Task</code>.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the <code>description</code> of this <code>Task</code>.
     *
     * @param description new <code>description</code> of this task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a formatted string with the type, <code>isDone</code> status
     * and <code>description</code> of this <code>Task</code>.
     *
     * @return the formatted string.
     */
    public String toString() {
        String mark;
        if (isDone) {
            mark = "X";
        } else {
            mark = " ";
        }
        return String.format("[T][%s] %s", mark, description);
    }

    /**
     * Returns a formatted string with the type, a number representing the <code>isDone</code>
     * status ('0' for false, '1' for true), and <code>description</code> of the <code>Task</code>
     * for saving to the hard disk.
     *
     * @return the formatted string.
     */
    public String toSave() {
        return String.format("T / %s / %s", isDone? "1" : "0", description);
    }
}
