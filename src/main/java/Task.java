public class Task {
    private String taskName;
    private boolean taskDone = false;
    public Task(String taskName){
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }
    public String getTaskIdentity(){
        String taskIdentity;
        if(taskDone){
            taskIdentity = "[X] " + taskName;
        } else {
            taskIdentity ="[ ] " + taskName;
        }
        return taskIdentity;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public void setDoneTask(boolean taskDone) {
        this.taskDone = taskDone;
    }
}

