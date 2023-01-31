package Entities;
public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription, boolean isDone) {
        setTaskDescription(taskDescription);
        setIsDone(isDone);
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.getTaskDescription();
    }
}
