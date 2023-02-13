package Tasks;
public class Task {
    protected String description;
    public String getDescription() {
        return this.description;
    }
    protected boolean isDone;
    public int getIsDone() {
        return isDone? 1 : 0;
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getType() {
        return null;
    }
    public String getStatusIcon() {
        return(isDone ? "[X] " : "[ ] ");
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsNotDone() {
        this.isDone = false;
    }
    public String fullDescription() {
        String fullSentence = (isDone ? "[X] " : "[ ] ") + this.description;
        return fullSentence;
    }
}
