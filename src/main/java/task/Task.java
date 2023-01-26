package task;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setTaskState(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T][" + (isDone ? "x" : " ") + "] " + description;
    }
}
