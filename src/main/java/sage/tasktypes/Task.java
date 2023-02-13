package sage.tasktypes;

public class Task {
    private String taskName = "";
    private boolean isCompleted = false;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String toString() {
        return "";
    }

    ;
}
