public class Task {
    private boolean isComplete;
    private String taskName;

    public Task(String taskName) {
        isComplete = false;
        this.taskName = taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

}
