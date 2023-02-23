package duke.task;

public class Task {

    private boolean isDone;
    private final String taskName;
    static final String DONE = "[X] ";
    static final String NOT_DONE = "[ ] ";

    /**
     * Marks tasks as done or not done
     *
     * @param status represents if a task is completed or not
     */
    public void setStatus(boolean status) {
        isDone = status;
    }

    public Task(String description) {
        this.taskName = description;
        this.isDone = false;
    }

    /**
     * Returns the status of tasks
     *
     * @return completion status of tasks
     */
    public String completed() {
        if (isDone) {
            return DONE;
        } else {
            return NOT_DONE;
        }
    }

    /**
     * Checks if current task matches user input
     *
     * @param details the user input to compare stored tasks with
     * @return returns true if task contains user input
     */
    public boolean isMatchingTask(String details) {
        return taskName.contains(details);
    }

    public String getTaskNameAndStatus() {
        return completed() + taskName;
    }

    public String returnCommand() {
        return null;
    }

    public String toString() {
        return completed() + taskName;
    }
}
