package task;

public class Event extends Task {
    private final String start;
    private final String end;

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

    public String getTaskType() {
        return "E";
    }

    @Override
    public String getSummary() {
        return super.getSummary() + " (from: " + getStart() + " " +
                "to: " + getEnd() + ")";
    }

    @Override
    public String getDataSummary() {
        return super.getDataSummary() + " | " + getStart() + " | " + getEnd();
    }
}
