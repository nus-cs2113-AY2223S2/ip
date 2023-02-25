package task;


public class Event extends Task {
    public String from;
    public String to;

    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    //@Override
    public String toString() {
        String newString = super.toString().replaceFirst("T","E");
        return newString + " (from: " + from + " to: " + to + ")";
    }
}
