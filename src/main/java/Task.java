public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * constructs a task
     *
     * @param description String containing description of task added
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * checks whether a task is done or not and obtains the status icon of the task
     *
     * @return x if the task is marked as done, whitespace is the task is not marked as done
     */
    public String getStatusIcon() {
        return (isDone ? "x" : " ");
    }

    /**
     * formats a String to be saved to the file
     *
     * @return formatted String to be saved into the file
     */
    public abstract String toSerialisedString();

    /**
     * formats a String to contains the task status and description
     *
     * @return String containing task status and information
     */
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + description);
    }
}
