public class Task {
    private String taskName;
    private Boolean isDone;

    public Task() {
        taskName = "";
        isDone = false;
    }

    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
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

    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}


