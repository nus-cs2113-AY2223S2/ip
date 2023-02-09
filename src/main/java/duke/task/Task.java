package duke.task;

public class Task {
    private String name;
    private boolean isDone;
    private static int numTask;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
        numTask++;
    }

    public String getName() {
        return name;
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
        return "[" + getStatusIcon() + "] " + getName();
    }
}
