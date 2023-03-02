public class Event extends Task {
     String beg, end;
    public Event(String beg, String end, String description) {
        super(description);
        this.beg = beg;
        this.end = end;
    }

    public String toString() {
        return ("[E]" + super.toString() + "(from: " + beg + "to: " + end + ")");
    }
}