public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime.substring(5);
        this.endTime = endTime.substring(3);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startTime + "to: " + endTime + ")";
    }
}
