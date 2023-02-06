public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description, 'E');
        this.from = from;
        this.to = to;
    }

    @Override
    public String getListDescription() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + getDescription() +
                " (from: " + from + " to: " + to + ")";
    }
}
