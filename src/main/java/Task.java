public class Task {
    private String taskName;
    private boolean isDone = false;
    public Task(String taskName){
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }
    /**
     * Returns identity of the current task including the following
     * - [X] or [ ] for marked or unmarked tasks
     * - task name
     * @return tasks identity.
     */
    public String getTaskIdentity(){
        String taskIdentity;
        if(isDone){
            taskIdentity = "[X] " + taskName;
        } else {
            taskIdentity ="[ ] " + taskName;
        }
        return taskIdentity;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}

