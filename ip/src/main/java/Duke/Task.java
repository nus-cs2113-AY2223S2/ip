package Duke;

public class Task {
    protected String description;
    protected boolean isDone;  // protected => public
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "[T]";  //Default type is [T]
    }

    public String getType() {
        return type;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString () {
        return getStatusIcon() + description;
    }
}