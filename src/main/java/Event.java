public class Event extends Task {

    private String start;
    private String end;


    public Event(String description, boolean isMark, String start, String end) throws DukeException {
        super(description, isMark);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.start + "to:" + this.end + ")";
    }
}