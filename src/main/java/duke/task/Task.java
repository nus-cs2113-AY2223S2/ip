package duke.task;

import java.time.LocalDateTime;

public class Task {
    protected String type;
    protected String task;
    private boolean isDone;
    protected LocalDateTime endTime;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
        this.type = "todo";
        this.endTime = LocalDateTime.of(0, 1, 1, 0, 0);
    }

    public String getDescription() {
        return this.task;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * sets the current task to be marked as done
     *
     * @return the String of the marked task
     */
    public String setAsDone() {
        this.isDone = true;
        return this.task;
    }

    /**
     * sets the current task to be marked as not done
     *
     * @return the String of the marked task
     */
    public String setAsUndone() {
        this.isDone = false;
        return this.task;
    }

    /**
     * checks if the task is done, if done will return a tick,
     * however, if not done will return an empty space
     *
     * @return the String indicating the status of the task
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * gets the type of task (todo type)
     *
     * @return letter representing type of task
     */
    public String getTaskType() {
        return "[T]";
    }

    /**
     * Shows the full task status and description
     *
     * @return task status and description
     */
    public String getTaskStatus() {
        return "[T]" + "[" + getStatusIcon() + "] " + task;
    }
}
