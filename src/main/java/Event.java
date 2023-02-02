public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String taskType, String start, String end) {
        super(description, taskType);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + start + " to:" + end + ")";
    }
}