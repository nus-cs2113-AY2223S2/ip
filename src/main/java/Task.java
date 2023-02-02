public class Task {
    protected String description;
    protected boolean isDone;

    protected String doneStatus;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDoneStatus() {
        if (isDone) {
            doneStatus = "[X]";
        }
        else {
            doneStatus = "[ ]";
        }
        return doneStatus;
    }

    public void setDoneStatus(String doneStatus) {
        this.doneStatus = doneStatus;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
