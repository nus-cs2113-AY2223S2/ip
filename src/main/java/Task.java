public class Task {
    private String taskName;
    private boolean isComplete;

    public Task (String taskName) {
        this.taskName = taskName;
        this.isComplete = false;
    }

    public String getTask() {
        return taskName;
    }

    public Character getStatus() {
        return ( isComplete ? 'X' : ' ');
    }

    public void setComplete() {
        this.isComplete = true;
    }

    public void setIncomplete() {
        this.isComplete = false;
    }
}

