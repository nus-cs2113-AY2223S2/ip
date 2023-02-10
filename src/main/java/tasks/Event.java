package tasks;

import tasks.Task;

public class Event extends Task {
    public String startDate;
    public String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + super.description.toString() + "(from: " + startDate + "to:"
                + endDate + ")";
    }
}
