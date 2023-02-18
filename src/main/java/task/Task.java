package task;

/**
 * Represents a Task that contains attributes such as the details of the task and
 * whether the task has been completed.
 */
public class Task {
    protected String details;
    protected boolean isDone;

    /**
     * Constructs a Task object that assigns the details of the task and completion status to undone.
     *
     * @param details the string containing the detailed description of the task.
     */
    public Task(String details) {
        this.details = details;
        this.isDone = false;
    }

    /**
     * Getter method that gets the 'details' attribute of the Task object.
     *
     * @return a string that contains the description of the 'details' attribute.
     */
    public String getDetails() {
        return this.details;
    }

    /**
     * Getter method that gets the status icon of the Task object based on the 'isDone' attribute.
     *
     * @return a string that is either "X" meaning task is done, or " " if task is not done.
     */
    public String getStatusIcon() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    /**
     * Getter method that gets the 'isDone' attribute of the Task object.
     *
     * @return a boolean that states whether the task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Setter method that sets the 'isDone' attribute to true in a Task object.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Setter method that sets the 'isDone' attribute to false in a Task object.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Method to print Task objects in a certain manner.
     *
     * @return a string that represents the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.details;
    }


}
