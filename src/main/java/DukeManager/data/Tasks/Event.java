package DukeManager.data.Tasks;

public class Event extends Task {
    protected String to;
    protected String from;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E] %s /from %s /to %s",super.toString(), this.from, this.to);
    }
}
