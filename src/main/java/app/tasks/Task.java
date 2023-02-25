package app.tasks;

public class Task {

    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription, boolean isDone) {
        setTaskDescription(taskDescription);
        setDone(isDone);
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone() ? "X" : " "); //Marks a task done with an X
    }

    public String toString() {
        return null;
    }
}

