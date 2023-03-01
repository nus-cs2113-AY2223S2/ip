/**
 * Class to represent tasks created by Duke
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task
     *
     * @param description the name of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Sets the status of the task as done or not done
     * based on the variable passes in
     *
     * @param done to determine whether to check or uncheck the task
     */

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * overrides the string to return the status of the task
     * along with its description
     *
     * @return a string representing the status and details of the task
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }
}
