package tasks;

public class Task {
    protected String description;
    protected int num;
    protected boolean isDone;

    public Task(String description, int num) {
        this.description = description;
        this.isDone = false;
        this.num = num;
    }

    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString(){
        return "[" + getStatusIcon() + "]"+ description;
    }

}
