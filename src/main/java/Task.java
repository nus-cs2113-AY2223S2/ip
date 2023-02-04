public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(){
        isDone = true;
    }

    public void unmarkAsDone(){
        isDone = false;
    }

    public String getType() {
        return null;
    }

    public String getTimings() {
        return null;
    }
}
