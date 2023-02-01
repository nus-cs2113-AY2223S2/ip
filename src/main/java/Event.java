public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, char type, String from, String to) {
        super(description, type);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getListDescription() {
        return "[" + super.getType() + "]" + "[" + getStatusIcon() + "] " + super.getDescription() +
                " (from: " + from + " to: " + to + ")";
    }
}
