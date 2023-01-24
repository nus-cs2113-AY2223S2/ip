public class Task {
    private String taskName;
    private boolean isDone;

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void markDone(){
        isDone = true;
    }

    public void unmarkDone(){
        isDone = false;
    }
}
