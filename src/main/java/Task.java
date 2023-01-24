public class Task {
    protected String description;
    protected boolean isDone;

    public String getTask(){
        return this.description;
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void MarkStatusDone() {
        this.isDone = true;
    }
    public void MarkStatusUndone() {
        this.isDone = false;
    }
}
