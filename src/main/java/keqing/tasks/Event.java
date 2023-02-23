package keqing.tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    public static final String sign = "E";

    public Event (String description, String from, String to) {
        super(description);
        Task.taskCount += 1;
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskType() {
        return sign;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}