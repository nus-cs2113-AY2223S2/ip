package duke.task;

public class Task {
    protected int Index;
    protected String description;
    protected boolean isDone;

    public Task(int Index, String description) {
        this.Index = Index;
        this.description = description;
        this.isDone = false;
    }

    public void setIndex(int taskIndex) {
        this.Index = taskIndex;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIndex() {
        return Index;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String toString() {
        return Index + ".";
    }
}
