public class Task {
    private String description;
    private boolean isDone;
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getStatusIcon() {
        return isDone ? "X" : " "; // mark done task with X, undone as empty
    }
    
    public void markTask(boolean isDone) {
        this.isDone = isDone;
    }
}
