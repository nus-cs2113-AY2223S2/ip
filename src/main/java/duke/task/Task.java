package duke.task;

public abstract class Task {
    private String desciption;
    private boolean isDone;
    private static int numTask;

    public Task(String desciption) {
        this.desciption = desciption;
        this.isDone = false;
        numTask++;
    }

    public String getTaskDescription() {
        return desciption;
    }

    public int getNumTask() {
        return numTask;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setStatus(boolean status) {
        this.isDone = status;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getTaskDescription();
    }
    
    public abstract String getTaskType();
}
