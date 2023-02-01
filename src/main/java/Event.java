public class Event extends Task {
    private String from;
    private String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
        setDescription(getDescription() + '(' + from);
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
        setDescription(getDescription() + to + ')');
    }

    public Event(String description, String from, String to) {
        super(description);
        setFrom(from);
        setTo(to);
    }
}
