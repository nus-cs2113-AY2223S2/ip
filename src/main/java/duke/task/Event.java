package duke.task;

public class Event extends Task{
    protected String startDate;
    protected String endDate;

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    /**
     * Constructor method for Event object.
     * @param description This is the description of the Event.
     * @param startDate This is the startDate of the Event.
     * @param endDate This is the endDate of the Event.
     */
    public Event(String description, String startDate, String endDate) {
        super(description, "Event");
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the details of Event in a specific format.
     * @return String This returns the details of the Event.
     */
    public String toString(){
        return "[E]" + super.toString() + " (From: " + this.getStartDate() + " to: " + this.getEndDate() + ")";
    }
}
