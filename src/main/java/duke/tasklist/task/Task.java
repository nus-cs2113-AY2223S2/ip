package duke.tasklist.task;

public abstract class Task {
    private String taskName;
    private boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskStatus() {
        if (isCompleted) {
            return "X";
        }
        return " ";
    }

    public String getTaskStatusForFile() {
        if (isCompleted) {
            return "1";
        }
        return "0";
    }

    public void setCompleted() {
        isCompleted = true;
    }

    public void setIncomplete() {
        isCompleted = false;
    }

    @Override
    public String toString() {
        return "[" + getTaskStatus() + "] " + getTaskName();
    }

    public String toFile() {
        return "| " + getTaskStatusForFile() + " | " + getTaskName();

    }
}