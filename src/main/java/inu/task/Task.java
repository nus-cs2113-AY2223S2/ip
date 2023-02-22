package inu.task;

public abstract class Task {

    private String description;

    private boolean isDone;

    public Task(String description) {
        setDescription(description);
        resetDone();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone() {
        isDone = true;
    }

    public void resetDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), getDescription());
    }

    public String encodeTask() {
        return "";
    }

}
