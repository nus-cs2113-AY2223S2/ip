package duke.tasks;

/**
 * Represents a general task. All task objects have their specific types , status(done or not)
 * and their task descriptions
 */

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;


    /**
     * Constructor for a general task
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter functions for the specified task types , task descriptions and statuses
     *
     * @return the type of the task , description of the task and
     * status of task(True if it is marked as done, else False)
     */
    public String getType() {
        return type;
    }

    public String toString() {
        return "";
    }

    public String getDescription() {
        return description;
    }


    public boolean isDone() {
        return isDone;
    }

    /**
     * Update the icon of the task according to its status
     *
     * @return "X" if it is done and " " if it is not done
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ] ");
    }

    /**
     * Marks the current task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmark the current task as done
     */
    public void markAsUndone() {
        isDone = false;
    }

}
