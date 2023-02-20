package tasks;

public class Event extends Task {
    protected String from;
    protected String to;


    public Event(String description, String from, String to) {
        super(description);
        this.type= "event";
        this.from = from;
        this.to = to;
    }

    public String[] getDetails() {
        String[] details = new String[2];
        details[0] = this.from;
        details[1] = this.to;
        return details;
    }

    @Override
    public String toString() {
        return "[E][" + getComplete() + "] " + getTask() + " (From: " + from + " To: " + to + ")";
    }
}