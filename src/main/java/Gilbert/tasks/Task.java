package Gilbert.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the task.
     *
     * @param description   Description of the task.
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the status of the task as done.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Sets the status of the task as not done.
     */
    public void undo(){
        this.isDone = false;
    }

    /**
     * Gets the done status of the task.
     *
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Get the done status of the task in the form of an icon.
     *
     */
    public String getDone(){
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Gets the description of the task. To be overridden by subclasses.
     *
     */
    public String getDesc(){
        return description;
    }

    /**
     * Get method that is to be overridden by subclasses.
     *
     * @return The type of the task in the form of "D", "T" or "E".
     */
    public String getType() {
        return "";
    }

    /**
     * Get method that is to be overridden by subclasses.
     *
     * @return The save format for deadline.
     */
    public String getFormat() {
        return "";
    }

    /**
     * Printing format for all Gilbert.tasks. To be overridden by subclasses.
     *
     */
    @Override
    public String toString(){
        return description;
    }
}
