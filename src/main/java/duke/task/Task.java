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
    private static final String TASK_ICON = "[T]";
    private static final String TASK_TYPE = "todo";
    private static final String MARKED_ICON = "X";
    private static final String UNMARKED_ICON = " ";
    private static final LocalDateTime DEFAULT_TIME = LocalDateTime.of(0, 1, 1, 0, 0);

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
        this.type = TASK_TYPE;
        this.endTime = DEFAULT_TIME;
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
        return (this.isDone ? MARKED_ICON : UNMARKED_ICON);
    }

    /**
     * Gets the type of task (todo type).
     *
     * @return Letter representing type of task.
     */
    public String getTaskType() {
        return TASK_ICON;
    }

    /**
     * Shows the full task status and description.
     *
     * @return Task status and description.
     */
    public String getTaskStatus() {
        return TASK_ICON + "[" + getStatusIcon() + "] " + task;
    }

}
