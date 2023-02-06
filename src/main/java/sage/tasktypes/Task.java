package sage.tasktypes;

public class Task {
    private String taskName = "";
    private boolean completed = false;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String toString() {
        return "";
    }

    ;
}
