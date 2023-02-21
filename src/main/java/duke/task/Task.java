package duke.task;

/**
 * The main Class that all items used in the Duke programme will inherit from.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isTodo = false;
    protected boolean isDeadline = false;
    protected boolean isEvent = false;

    /**
     * To create a new task object.
     * @param description The description (or name) of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks a task as not done.
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * @return true if the task is a todo
     */
    public boolean isTodo() {
        return isTodo;
    }

    /**
     * @return true if the task is a deadline
     */
    public boolean isDeadline() {
        return isDeadline;
    }

    /**
     * @return true if the task is an event
     */
    public boolean isEvent() {
        return isEvent;
    }

    /**
     * Function to return the type of task
     * @return T if task is TODO, D if task is DEADLINE, E if task is EVENT
     */
    public String getTypeIcon() {
        if (isTodo) {
            return "T";
        } else if (isDeadline) {
            return "D";
        } else if (isEvent) {
            return "E";
        }
        return " ";
    }

    /**
     * Function to check if a task is done.
     * @return The done or not done status of a task.
     */
    public String getDoneIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Classifies a task as a todo task.
     */
    public void setTodo() {
        isTodo = true;
    }

    /**
     * Classifies a task as a deadline.
     */
    public void setDeadline() {
        isDeadline = true;
    }

    /**
     * Temporary method to be overridden in the Deadline class
     * @return null
     */
    public String getBy() {
        return null;
    }

    /**
     * Classifies a task as an event.
     */
    public void setEvent() {
        isEvent = true;
    }

    /**
     * Temporary method to be overridden in the Event class
     * @return null
     */
    public String getFrom() {
        return null;
    }

    /**
     * Temporary method to be overridden in the Event class
     * @return null
     */
    public String getTo() {
        return null;
    }
}