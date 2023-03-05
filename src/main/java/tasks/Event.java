package tasks;

/**
 * This is the Event Task Class, Stores a task with a description, endDate and Startdate
 * */
public class Event extends Task {
    private String startDate;
    private String endDate;

    /**
     * Factory Function for Event
     * @param description Sets the description of event
     * @param startDate Start date of event
     * @param endDate End date of event
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    /*
     * Prints the description of the task when listed out
     * */
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + super.getDescription() + "(from: " + startDate + " to:"
                + endDate + ")";
    }
}
