package duke.task;

public class Task {
    private boolean isDone;
    private String taskName;
    static final String DONE = "[X] ";
    static final String NOT_DONE = "[ ] ";

    public void setStatus(boolean status) {
        isDone = status;
    }

    public Task(String description) {
        this.taskName = description;
        this.isDone = false;
    }

    public String Completed() {
        if (isDone) {
            return DONE;
        } else {
            return NOT_DONE;
        }
    }

    public String getTaskNameAndStatus() {
        return Completed() + taskName;
    }

    public String toString() {
        return Completed() + taskName;
    }
}
