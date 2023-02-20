package duke.task;

public class Task {
    private boolean isDone;
    private final String taskName;
    static final String DONE = "[X] ";
    static final String NOT_DONE = "[ ] ";

    public void setStatus(boolean status) {
        isDone = status;
    }

    public Task(String description) {
        this.taskName = description;
        this.isDone = false;
    }

    public String completed() {
        if (isDone) {
            return DONE;
        } else {
            return NOT_DONE;
        }
    }

    public boolean isMatchingTask(String details) {
       return taskName.contains(details);
    }

    public String getTaskNameAndStatus() {
        return completed() + taskName;
    }

    public String returnCommand() {
        return null;
    }

    public String toString() {
        return completed() + taskName;
    }
}
