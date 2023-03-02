package Tasks;

public class Task {
    public String description;
    public boolean isDone;
    public String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String formatTask() {
        String status; 
        if (this.isDone) {
            status = "1"; // Task is marked as done
        } else {
            status = "0"; //Task marked as not done
        }
        String formatOutput = status + "|" + description;

        return formatOutput;
    }
}
