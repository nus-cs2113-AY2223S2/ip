package tasks;

public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + super.toString() + "(" + this.start + "- " + this.end + ")";
    }
}