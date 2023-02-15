package tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    protected char type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public char getType(){
        return type;
    }
    public String getDescription() {
        return description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

}
