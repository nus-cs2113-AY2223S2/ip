package duke.task;
public class Event extends Task {
    protected String byDate;
    protected String fromDate;

    public Event(String task, boolean isDone, String fromDate, String toDate) {
        super(task, isDone);
        this.type = "E";
        this.byDate = toDate;
        this.fromDate = fromDate;
    }

    @Override
    public String getTask() {
        return super.getTask() + " % " + fromDate + " % " + byDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + fromDate + " to: " + byDate + ")";
    }
}
