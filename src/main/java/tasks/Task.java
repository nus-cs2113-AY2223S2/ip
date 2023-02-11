package tasks;

public class Task {
    private String taskName;
    private boolean isComplete;
    private String type;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isComplete = false;
    }

    public String getTask() {
        return taskName;
    }

    public String getType() { return type; }

    public Character getStatus() {
        if (isComplete) {
            return 'X';
        }
        return ' ';
    }

    public void setComplete() {
        this.isComplete = true;
    }

    public void setIncomplete() {
        this.isComplete = false;
    }
}

