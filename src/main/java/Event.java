public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getType(){
        return "E";
    }

    public String getPeriod(){
        return " (from: " + from + " to: " + to + ")";
    }
    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "  [E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}