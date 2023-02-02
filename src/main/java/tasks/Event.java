package tasks;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        if (from == null || to == null) {
            throw new IllegalArgumentException(
                    "Event \"from\" and \"to\" parameters cannot be null");
        }
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("E %s (from: %s to: %s)", super.toString(), getFrom(), getTo());
    }
}
