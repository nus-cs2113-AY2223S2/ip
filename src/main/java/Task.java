
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {   //ok to leave as public?
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getTask() {
        String Task = "[";
        return Task.concat(getStatusIcon() + "] " + getDescription());
    }

}
