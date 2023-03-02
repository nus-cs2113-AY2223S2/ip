package IPChat;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return description;
    }
    public String saveStuff() {
        int save = 0;
        if (this.isDone) {
            save = 1;
        }
        return "taskType" + save +  " | " +  this.description + "\n";
    }
}
