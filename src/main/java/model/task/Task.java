package model.task;

/**
 * The task class which is the super class for Deadline, Event and Todo
 */
public abstract class Task {
    private boolean isDone;
    private final String taskName;

    public Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getTaskName() {
        return taskName;
    }

    public abstract String getDescriptionText();
}
