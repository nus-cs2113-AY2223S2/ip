package IPChat;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    @Override
    public String saveStuff () {
        int save = 0;
        if (this.isDone) {
            save = 1;
        }
        return "event" + description + "/from" + from + " | " + "/to" + to + save;
    }
}