public class Task {
    private String taskName;
    private boolean doneTask = false;
    public Task(String taskName){
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isDoneTask() {
        return doneTask;
    }

    public void setDoneTask(boolean doneTask) {
        this.doneTask = doneTask;
    }
}

