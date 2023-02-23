package tasks;

public class Task {

    protected String description;

    protected String userInput;
    protected boolean isDone;

    protected char type;

    public Task(String userInput, String description) {
        this.description = description;
        this.userInput = userInput;
        this.isDone = false;
    }

    public String getUserInput() {
        return userInput;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

    public String getDescription() {
        return description;
    }
}
