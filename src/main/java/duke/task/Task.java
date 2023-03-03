package duke.task;

public class Task {
    public String description;
    protected String type;
    protected boolean isDone;

    /**
     * Creates a task item with the description and type that
     * is passed into the method and initialises the completion
     * status as incomplete.
     * @param description String of the description of be stored in task items.
     * @param type String of the type of the task item.
     */
    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Returns the current completion station of the task item.
     * @return "X" if task is completed, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the isDone parameter of task items accordingly.
     * @param isDone true if task is done and false otherwise.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the description, status and the type of task items.
     * @return String to print.
     */
    @Override
    public String toString() {
        String status = getStatusIcon();
        return "[" + type + "][" + status + "] " + description;
    }

}