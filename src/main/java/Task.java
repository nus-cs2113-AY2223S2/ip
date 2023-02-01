public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getTaskName() {
        return taskName;
    }

    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        }
        else {
            return "[ ] " + taskName;
        }
    }
}
