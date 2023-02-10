package duke.task;

public class Event extends Task {
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        String taskOutput = super.toString();
        return String.format("[E]%s (by: %s to: %s)", taskOutput, startDate, endDate);
    }
}
