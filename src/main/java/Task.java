public class Task {
    protected String taskName;
    protected boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskStatus() {
        if (isCompleted) {
            return "✓";
        }
        return " ";
    }

    public void setCompleted() {
        isCompleted = true;
    }

    public void setIncomplete() {
        isCompleted = false;
    }
}