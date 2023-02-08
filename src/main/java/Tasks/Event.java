package Tasks;
public class Event extends Task {
    protected String startTime;
    protected String endTime;
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String fullDescription() {
        String fullSentence = (isDone ? "[E][X] " : "[E][ ] ") + this.description +
                "(from:" + this.startTime + " to:" + this.endTime + ")";
        return fullSentence;
    }
}
