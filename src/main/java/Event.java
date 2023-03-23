public class Event extends Task{
    /***
     * The starting time of the event.
     */
    protected String by;
    /***
     * The ending time of the event.
     */
    protected String to;

    /***
     * Functions in the Event class.
     * @param description Name of the task.
     * @param by Starting time of the event.
     * @param to Ending time of the event.
     */
    public Event(String description,String by, String to) {
        super(description);
        this.by = by;
        this.to = to;
    }

    /***
     * The string output when class is called.
     * @return Status, starting time and ending time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + by + " to: " + to + ")";
    }
}
