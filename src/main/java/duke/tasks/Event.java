package duke.tasks;

public class Event extends Task {

    protected String fromDate;
    protected String toDate;

    public Event(String description, String fromDate, String toDate, boolean isDone) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getTaskType() {
        return "E";
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    @Override
    public String toString() {
        // String[] dates =  description.split("/from | /to");
        // String fromDate = dates[1];
        // String toDate = dates[2];
        return "[E]" + super.toString() + " (from: " + fromDate + " to: " + toDate + ")" ;
    }
}
