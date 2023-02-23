package duke.task;

public abstract class Task {
    
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description, char type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public char getTypeIcon() {
        return type;
    }

    public void mark(boolean done) {
        isDone = done;
    }

    public abstract String getTimeBound();

    @Override
    public String toString() {
        return ("[" + getTypeIcon() + "] " + "[" + getStatusIcon() + "] " + getDescription());
    }
}