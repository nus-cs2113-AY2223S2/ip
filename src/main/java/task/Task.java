package task;

public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDone(boolean done) {
        isDone = done;
    }
    public void markAsDone() {
        setDone(true);
        setDescription(description.replace("[ ]", "[X]"));
    }
    public void markAsUndone() {
        setDone(false);
        setDescription(description.replace("[X]", "[ ]"));
    }
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }
    public String saveStringToFile() {
        String status;
        if (getStatusIcon().equals(" ")) {
            status = "0";
        } else {
            status = "1";
        }
        return status + "|" + description;
    }
}
