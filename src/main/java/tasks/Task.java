package tasks;

public class Task {
    private final String task;
    private boolean isComplete;
    protected String type = "";

    public Task(String name) {
        this.task = name;
        this.isComplete = false;
    }

    public String getTask() {
        return this.task;
    }

    public char getComplete() {
        if (this.isComplete) {
            return 'X';
        }
        return ' ';
    }

    public void setComplete() {
        this.isComplete = true;
    }

    public void setIncomplete() {
        this.isComplete = false;
    }

    public String getType() {
        return this.type;
    }

}