package Support;

import BasisSupport.Status;

public class Task {
    // Task class has many attributes that can describe the information of a particular task
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

    // Related methods are used to set and get some information of the task
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
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

    // This method is to show the task in a particular format that will be stored in the txt file
    public String showTask() {
        return this.getStatus() + "[" + this.getStatusIcon() + "] " + this.description;
    }
}