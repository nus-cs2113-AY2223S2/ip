package Task;
public class Event extends Task {

    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        super.printMessage();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to " + end + ")";
    }
    @Override
    public String getTaskType() {
        return "E";
    }
    public String getStartTime() {
        return start;
    }

    public String getEndTime() {
        return end;
    }
}
