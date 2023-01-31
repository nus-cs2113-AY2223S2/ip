public class Task {
    protected String description;
    protected boolean isDone;  // protected => public

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean isDone() {  // do we need this
        return isDone;
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }
}
