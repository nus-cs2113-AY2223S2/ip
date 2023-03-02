package duke.task;

/**
 * Parent class for Task objects.
 */
public abstract class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Constructs a task with the given task name.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Sets the task's completion status.
     *
     * @param done Completion status of the task. True if completed, False if uncompleted.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Converts the task into its string representation which contains its completion status and task name.
     *
     * @return String representation of the task.
     */
    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Prints the string representation of the task
     */
    public void printTask() {
        System.out.println(this.toString());
    }
}
