package duke;

public class Task {
    protected String description;

    protected String taskType;

    protected boolean isDone;

    public Task(String description, String taskType) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;
    }

    public void markAsDone(){
        isDone = true;
    }

    public void markAsUndone(){
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String textToSave(){
        return "";
    }
}


