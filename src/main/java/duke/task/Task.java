package duke.task;

public class Task {
    protected String description;
    protected String startDate;
    protected String endDate;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.startDate = "";
        this.endDate = "";
        this.isDone = false;
    }

    public String getStatusIcon() {
        return ((this.isDone) ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setAsNotDone() {
        this.isDone = false;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        String itemStatus = this.getStatusIcon();
        String itemDescription = this.getDescription();
        return String.format("[%s] %s", itemStatus, itemDescription);
    }
}
