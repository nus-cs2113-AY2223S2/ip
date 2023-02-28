package ChatTPG;

/**
 * Class to represent the Tasks which will be created and added to the list of tasks.
 */
public abstract class Task {

    /** Description of the task */
    protected String description;

    /** True if the task has already been done and False otherwise */
    protected boolean isDone;

    /** Marker if specified task is done */
    protected static final String DONE = "X";

    /** Marker if specified task is not yet done */
    protected static final String NOT_DONE = " ";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        String task_status;

        if (isDone()) {
            task_status = DONE;
        } else {
            task_status = NOT_DONE;
        }

        String result = "[" + task_status + "] " + description;
        return result;
    }
}