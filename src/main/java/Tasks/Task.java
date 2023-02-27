package Tasks;
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Getter for task description
     *
     * @return string of task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for task done status
     *
     * @return integer 1 if task is done, else 0
     */
    public int getIsDone() {
        return isDone? 1 : 0;
    }

    /**
     * Constructor for task
     *
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter for task type, is override by subclasses
     *
     * @return null
     */
    public String getType() {
        return null;
    }

    /**
     * Getter for task status icon
     *
     * @return string that marks a task if done, else unmarked
     */
    public String getStatusIcon() {
        return(isDone ? "[X] " : "[ ] ");
    }

    /**
     * Setter to set task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Setter to set task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Getter for full description of a task
     *
     * @return string with task status and description
     */
    public String fullDescription() {
        String fullSentence = (isDone ? "[X] " : "[ ] ") + this.description;
        return fullSentence;
    }
}
