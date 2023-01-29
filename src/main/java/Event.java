public class Event extends Task {
    protected String eventFrom;
    protected String eventTo;

    public Event(String description, String from, String to) {
        super(description);
        this.eventFrom = from;
        this.eventTo = to;
    }

    @Override
    public String describeTask() {
        return "[E]" + super.describeTask() + " (from: " + eventFrom + " to: " + eventTo + ")";
    }
}
    
