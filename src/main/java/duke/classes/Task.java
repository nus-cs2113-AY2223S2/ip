package duke.classes;

/**
 * A class that represents a task with a description and a status indicating whether it is done or not.
 */
public class Task {

    /** The description of the task */
    protected String description;

    /** A boolean indicating whether the task is done or not */
    protected boolean isDone;

    /**
     * Creates a new Task object with the given description and sets its status to not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task as a string. If the task is done, the status icon is "X"; otherwise, it is a space character.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {

        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Marks the task as done by setting its status to true */
    public void markDone() {
        this.isDone = true;
    }

    /** Marks the task as not done by setting its status to false */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, which is its description.
     *
     * @return the description of the task
     */
    public String toString() {
        return this.description;
    }
}
