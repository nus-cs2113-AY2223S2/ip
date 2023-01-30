public class Task {
    protected String task;
    protected boolean isDone;
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }
    public void markDone() {
        this.isDone = true;
    }
    public void unmarkDone() {
        this.isDone = false;
    }
    public String getTask() {
        return task;
    }
    public String getStatus() {
        return (isDone ? "X" : " ");
    }
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.getTask();
    }
}
