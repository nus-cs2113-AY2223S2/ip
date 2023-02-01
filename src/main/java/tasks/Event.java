package tasks;

public class Event extends Task {

    String from; // datetime as a string
    String to; // datetime as a string
    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }
    public String getTaskSymbol() {
        // E for tasks.Event
        return "E";
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
    public String toString(){
        return String.format("%s (from: %s to: %s)", description, from, to);
    }
}
