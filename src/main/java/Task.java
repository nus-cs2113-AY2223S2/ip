public class Task {
    private boolean isDone;
    private String taskName;
    String done = "[X] ";
    String notDone = "[ ] ";

    public void setStatus(boolean status) {
        isDone = status;
    }

    public Task(String description) {
        this.taskName = description;
        this.isDone = false;
    }

    public String Completed() {
        if (isDone) {
            return done;
        } else {
            return notDone;
        }
    }

    public String getTaskNameAndStatus() {
        return Completed() + taskName;
    }
}
