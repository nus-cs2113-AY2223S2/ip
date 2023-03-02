package duke.data;

/**
 * the event class with from time and to time
 */
public class Event extends Task{
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    protected String from;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    protected String to;

    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toString(){
        return "[E]" + super.toString() +" (from: " + from + " to: " + to +")";
    }
}
