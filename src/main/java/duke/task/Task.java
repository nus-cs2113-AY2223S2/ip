package duke.task;

public abstract class Task {

    protected final String MARKED = "X";
    protected final String UNMARKED = " ";
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
        return ((this.isDone) ? MARKED : UNMARKED);
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
