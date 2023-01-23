public class Tasks {
    private String task;
    private boolean isDone;
    public Tasks(String task) {
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

}
