package hina.task;

public class Task {
    protected String description;
    protected boolean isDone;
    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        String mark;
        if (isDone) {
            mark = "X";
        } else {
            mark = " ";
        }
        return String.format("[T][%s] %s", mark, description);
    }
}
