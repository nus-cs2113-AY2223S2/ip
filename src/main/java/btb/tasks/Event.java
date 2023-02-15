package btb.tasks;

public class Event extends Task {
    protected String startDate;
    protected String endDate;

    /**
     * Constructs a new instance of Event object.
     * Stores the description, startDate, endDate,
     * and status of the new event.
     * Status is set to false at initialisation.
     *
     * @param Description event description
     * @param startDate   start date and time of event
     * @param endDate     end date and time of event
     */
    public Event(String Description, String startDate, String endDate) {
        super(Description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * converts the event object to a string.
     *
     * @return the object as a string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + startDate + " to: " +
                endDate + ")";
    }
}
