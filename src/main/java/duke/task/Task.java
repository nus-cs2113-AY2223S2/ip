package duke.task;

public class Task {
    protected String description;
    protected boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public String getStatusIcon() {
        return (isMarked ? "[/]" : "[ ]");
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }
}
