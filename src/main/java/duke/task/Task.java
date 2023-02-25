package duke.task;

import duke.ui.TextUi;

public abstract class Task {
    private boolean isDone;
    private String desciption;

    protected TextUi ui = new TextUi();

    public Task(String desciption) {
        this.desciption = desciption;
        this.isDone = false;
    }

    public String getTaskDescription() {
        return desciption;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setStatus(boolean status) {
        this.isDone = status;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getTaskDescription();
    }

    public abstract String getTaskType();
}
