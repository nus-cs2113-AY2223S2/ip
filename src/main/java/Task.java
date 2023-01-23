public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String getTaskStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone(){
        this.isDone = true;
    }

    public void undo(){
        this.isDone = false;
    }

}
