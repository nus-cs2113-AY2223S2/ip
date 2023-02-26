package task;

public class Event extends Task {
    private String fromDate;
    private String toDate;

    public Event(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }

    @Override
    public String printToFile() {
        return "E | " + this.saveStatusIcon() + " | " + description + " | " + fromDate + "-" + toDate;
    }
}
