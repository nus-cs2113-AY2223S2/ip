package siri.task;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task() {
        description = null;
        isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getFileStatusIcon(){
        return (isDone? "X" : "0");
    }

    public String toFileString() {
        return getFileStatusIcon() + " | " + description;
    }
}