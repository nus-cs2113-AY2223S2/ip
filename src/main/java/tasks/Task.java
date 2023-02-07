package tasks;

public class Task {
    protected String description;
    protected static int num = 0;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        num++;
    }

    public static int getNum(){
        return num;
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
