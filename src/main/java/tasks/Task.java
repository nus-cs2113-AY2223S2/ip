package tasks;

public class Task {
    private String taskName;
    private boolean isComplete;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isComplete = false;
    }

    public String getTask() {
        return taskName;
    }

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

    public String printTask() {
        return "[" + getStatus() + "]" + getTask();
    }
}

