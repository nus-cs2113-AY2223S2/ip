package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a new Task object. Stores a description and status of the
     * task. The task is set to unmarked at initialization
     *
     * @param description the description of the new task created
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representing the task status
     *
     * @return X for task completed and a blank space for task uncompleted
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Set the task status as completed
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Set the task status as uncompleted
     */
    public void markAsUndone(){
        this.isDone = false;
    }

    /**
     * Returns the task description and status in a format that would be used during
     * task listing
     *
     * @return the string of status and description in task listing format
     */
    public String printTask(){
        return ("  " + "[" + getStatusIcon() + "]" + " " + getDescription());
    }

    /**
     * Returns the status of the task
     *
     * @return 1 for completed task and 0 for uncompleted task
     */
    public String checkCompletion() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Getter for task description
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the Task object to be saved in the data file
     *
     * @return the string representation of the task to be saved
     */
    public String saveTask() {
        return "";
    }
}
