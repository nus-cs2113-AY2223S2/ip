package duke.task;

import java.time.LocalDateTime;

/**
 * Basic todo task with only description
 */
public class Task {
    protected String type;
    protected String task;
    private boolean isDone;
    protected LocalDateTime endTime;

    /**
     * Constructs a new todo task with default date
     * of 1/1/0000 0000 so that it will be placed at the top when
     * sorted.
     *
     * @param task Description of task.
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
        this.type = "todo";
        this.endTime = LocalDateTime.of(0, 1, 1, 0, 0);
    }

    /**
     * Returns the task description.
     *
     * @return String containing task description.
     */
    public String getDescription() {
        return this.task;
    }

    /**
     * Returns the endTime of task.
     *
     * @return LocalDateTime object of endTime.
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Sets the current task to be marked as done.
     *
     * @return The String of the marked task
     */
    public String setAsDone() {
        this.isDone = true;
        return this.task;
    }

    /**
     * Sets the current task to be marked as not done.
     *
     * @return The String of the marked task.
     */
    public String setAsUndone() {
        this.isDone = false;
        return this.task;
    }

    /**
     * Checks if the task is done, if done will return a tick,
     * however, if not done will return an empty space.
     *
     * @return The String indicating the status of the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Gets the type of task (todo type).
     *
     * @return Letter representing type of task.
     */
    public String getTaskType() {
        return "[T]";
    }

    /**
     * Shows the full task status and description.
     *
     * @return Task status and description.
     */
    public String getTaskStatus() {
        return "[T]" + "[" + getStatusIcon() + "] " + task;
    }

}
