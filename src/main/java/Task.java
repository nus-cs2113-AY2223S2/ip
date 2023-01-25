public class Task {
    private String taskName = "";
    private boolean completed = false;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    public String GetTaskName() {
        return this.taskName;
    }

    public void SetCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean IsCompleted() {
        return completed;
    }
}
