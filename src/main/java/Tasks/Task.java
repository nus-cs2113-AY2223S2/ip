package Tasks;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String describeTask() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String describeTaskForFile() {
        return  "";
    }
}
