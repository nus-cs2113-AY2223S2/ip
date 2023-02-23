package tasks;

public class Task {
    protected String description;
    private boolean isDone;
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public String getDescription() {
        return description;
    }
    
    public boolean getIsDone() {
        return isDone;
    }
    
    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }
    
    public void markTask(boolean isDone) {
        this.isDone = isDone;
    }
    
    public String toString() {
        return getStatusIcon() + description;
    }
    
    public String getTaskType() {
        return "";
    }
    
    public String getTaskContent() {
        return "";
    }
}
