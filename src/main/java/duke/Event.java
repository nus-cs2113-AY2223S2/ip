package duke;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String storeString() {
        return super.storeString() + "|E|" + description + "|" + from + "|" + to + "\n";
    }

    @Override
    public boolean matchesKeyword (String keyword) {
        if (description.contains(keyword) || from.contains(keyword) || to.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    /**
     * Overrides string representation for todos, with a [E] to indicate event class
     * and
     * conveying information about time.
     *
     * @return String Representation.
     */
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}