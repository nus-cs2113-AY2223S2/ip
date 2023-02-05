package tasks;

public class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description){
        this.isDone = false;
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskSymbol() {
        // U for Unknown/Undefined
        return "U";
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return description;
    }

    public String getDetailedString(){
        return String.format("[%s][%s] %s", getTaskSymbol(), getStatusIcon(), this);
    }

}
