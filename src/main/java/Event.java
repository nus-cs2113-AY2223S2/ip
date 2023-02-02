public class Event extends Task {
    private String start;
    private String end;

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String getSummary() {
        return super.getSummary() + " (from: " + getStart() + " " +
                "to: " + getEnd() + ")";
    }
}
