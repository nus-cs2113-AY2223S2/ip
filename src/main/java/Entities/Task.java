package Entities;
public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        setTaskName(taskName);
        setIsDone(false);
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.getTaskName();
    }
}
