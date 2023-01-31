public class Task {
    private final String taskName;
    private boolean isDone;

    //Instantiate
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }
    public boolean getIsDone() {
        return isDone;
    }
    public void setDone() {
        isDone = true;
    }
    public void setNotDone() {
        isDone = false;
    }
    public String getTaskName() {
        return taskName;
    }
}
