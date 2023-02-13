package Tasks;
public class Event extends Task {
    protected String startTime;
    protected String endTime;
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public String getFrom() {
        return startTime;
    }
    public String getTo() {
        return endTime;
    }
    @Override
    public String getType() {
        return "event";
    }
    @Override
    public String fullDescription() {
        String fullSentence = (isDone ? "[E][X] " : "[E][ ] ") + this.description +
                "(from:" + this.startTime + " to:" + this.endTime + ")";
        return fullSentence;
    }
}
