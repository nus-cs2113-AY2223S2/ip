/**
 * A Task object contains a description of the task, and isDone boolean to indicate whether this task has been completed.
 */
public class Task {
    private String description;
    private boolean isDone;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * @param description The description of the task.
     */
    public Task(String description) {
        setDescription(description);
        setDone(false);
    }

    private String getStatusIcon() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * @return A formatted string that indicates the type of the task using a single letter 'T', 'D', or 'E' in brackets
     * "[]" corresponding to a "Todo", "Deadline", or "Event" respectively. Followed by a space ' ' or a 'X' indicating an
     * undone or done task respectively. Followed by the description of the task.
     */
    public String getTaskString() {
        return '[' + this.getClass().getSimpleName().substring(0, 1) + "][" + getStatusIcon() + "] " + getDescription();
    }
}

