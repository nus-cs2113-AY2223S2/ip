public class Event extends Task {
    String from;
    String to;

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    Event(String description, String from, String to) {
        super(description);
        setFrom(from);
        setTo(to);
    }

    @Override
    public char getTypeIcon() {
        return 'E';
    }

    @Override
    public String getDescription() {
        return super.getDescription() + from + to;
    }
}
