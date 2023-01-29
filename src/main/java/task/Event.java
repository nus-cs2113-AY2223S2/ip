package task;

public class Event extends Deadline {
    protected String startDate;
    public Event(String description, String startDate, String endDate) throws EmptyDescriptionException {
        super(description,endDate);
        setStartDate(startDate);
    }
    @Override
    public String toString() {
        return String.format("[E][%c] %s (from: %s to: %s)", getStatusIcon(), description, startDate, endDate);
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String newStartDate) {
        startDate = newStartDate;
    }
}
