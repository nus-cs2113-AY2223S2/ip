package tasks;
public class Event extends Todo {
    private final String TYPE_REPRESENTATION = "E";

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }
    
    @Override
    public String getSaveRepresentation() {
        int doneRepresentation = isDone() ? 1 : 0;
        return String.format("%s /// %d /// %s /// %s /// %s", TYPE_REPRESENTATION, doneRepresentation, getDescription(), getFrom(), getTo());
    }
    
    public String toString() {
        char mark;
        if (isDone) {
            mark = 'X';
        } else {
            mark = ' ';
        }
        return String.format("[%s][%c] %s (from: %s to: %s)", TYPE_REPRESENTATION, mark, description, from, to);
    }
}