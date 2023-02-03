package duke.task;
public class Event extends Task {
    protected String byDate;
    protected String fromDate;

    public Event(String task, int number, boolean isDone, String fromDate, String toDate) {
        super(task, number, isDone);
        this.type = "E";
        this.byDate = toDate;
        this.fromDate = fromDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + fromDate + " to: " + byDate + ")";
    }
}
