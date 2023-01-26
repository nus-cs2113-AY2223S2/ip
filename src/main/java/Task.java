public class Task {
    private String taskName;
    private Boolean isDone;

    public Task() {
        taskName = "";
        isDone = false;
    }

    public Task(String taskName, Boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    public Boolean getisDone() {
        return isDone;
    }

    public void setTaskName (String taskName) {
        this.taskName = taskName;
    }

    public void setisDone(Boolean isDone) {
        this.isDone = isDone;
    }
}


