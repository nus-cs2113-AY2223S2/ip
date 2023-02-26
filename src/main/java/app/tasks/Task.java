package app.tasks;

/**
 * The class used to represent any form of Task.
 */
public abstract class Task {

    protected String taskDescription;
    protected boolean isDone;

    /**
     * Constructor to create a new Task type.
     * @param taskDescription Description of the Task.
     * @param isDone Boolean value indicating if the Task is done or not.
     */
    public Task(String taskDescription, boolean isDone) {
        setTaskDescription(taskDescription);
        setDone(isDone);
    }

    /**
     * Setter to set the description of a Task.
     * @param taskDescription The description of the Task.
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Getter to obtain the description of a Task.
     * @return The description of the Task.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Getter to obtain the completion status of a Task.
     * @return A boolean value indicating if the Task is done or not.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Setter to set the completion status of a Task.
     * @param isDone A boolean value indicating if the Task is done or not.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Method used to determine status icon of a Task depending on whether its done.
     * @return A string containing an 'X' if it's done and an empty space otherwise.
     */
    public String getStatusIcon() {
        return (isDone() ? "X" : " "); //Marks a task done with an X
    }

    /**
     * Method to print a Task in string format.
     * @return A string representation of a Task.
     */
    public String toString() {
        return null;
    }
}

