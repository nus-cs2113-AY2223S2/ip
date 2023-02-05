package tasks;

public class Event extends Task {

    protected String from; // datetime as a string
    protected String to; // datetime as a string
    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String getTaskSymbol() {
        // E for tasks.Event
        return "E";
    }

    @Override
    public String toString(){
        return String.format("%s (from: %s to: %s)", description, from, to);
    }
}
