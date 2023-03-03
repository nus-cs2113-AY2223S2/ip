package nano.data.task;

public class Event extends Task {
    private static final String TASK_TYPE_TAG = "[E]";
    private String startDate;
    private String endDate;

    public Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toString() {
        return TASK_TYPE_TAG + super.toString() + " (from: " + startDate + ", to: " + endDate + ")";
    }
}
