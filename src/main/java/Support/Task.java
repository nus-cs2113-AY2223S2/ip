package Support;

import BasisSupport.Status;

public class Task {
    protected String description;
    protected boolean isDone;

    protected Status taskStatus;

    public Task(String description, Status taskStatus) {
        this.description = description;
        this.taskStatus = taskStatus;
        this.isDone = false;
    }

    public Task(String description, Status taskStatus, boolean isDone) {
        this.description = description;
        this.taskStatus = taskStatus;
        this.isDone = isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatus() {
        if (this.taskStatus.equals(Status.T)) {
            return "[T]";
        } else if (this.taskStatus.equals(Status.D)) {
            return "[D]";
        } else if (this.taskStatus.equals(Status.E)) {
            return "[E]";
        } else {
            return "";
        }
    }

    public String showTask() {
        return this.getStatus() + "[" + this.getStatusIcon() + "] " + this.description;
    }

}