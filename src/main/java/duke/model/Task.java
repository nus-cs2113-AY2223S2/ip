package duke.model;

import duke.exception.InvalidCommandException;

/**
 * A class to store the Task entity
 */
public class Task {
    /**
     * Represents the name of the task
     */
    protected String taskName;

    /**
     * Represents the state of the task, whether is done or not
     */
    protected boolean isDone;

    /**
     * Empty constructor that does nothing
     */
    public Task() {
    }


    /**
     * Task Constructor that takes an array of String to describe the entity
     *
     * @param descriptionArray The array of String of information
     * @throws InvalidCommandException When it is not given sufficient information
     */
    public Task(String[] descriptionArray) throws InvalidCommandException {
        if (descriptionArray.length < 1) {
            throw new InvalidCommandException("Description of task cannot be empty!");
        }
        this.taskName = descriptionArray[0];
        this.isDone = false;
    }

    /**
     * Task Constructor that takes an array of String to describe the entity and extra argument to initialize the todo whether is done or not.
     *
     * @param descriptionArray The array of String of information
     * @param isDone           The state of a Task object
     * @throws InvalidCommandException When it is not given sufficient information
     */
    public Task(String[] descriptionArray, boolean isDone) throws InvalidCommandException {
        this(descriptionArray);
        this.isDone = isDone;
    }


    /**
     * Method to mark as done a task
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method to unmark as done a task
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Method to return the state of a task using string. X means that it is done, while empty space means that it is still not done.
     *
     * @return String representation of the state of the task
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Method to represent Task as a string
     *
     * @return String representation of Task
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + "\t" + this.taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }
}
