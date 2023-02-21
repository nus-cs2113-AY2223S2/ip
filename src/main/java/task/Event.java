package task;

/**
 * Represents an Event type of task that has a timeframe, from a certain date/time
 * to a certain date/time, which is keyed in by the user.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs an Event object that inherits from Task object, assigns the start date and end date of task.
     *
     * @param details the string containing the detailed description of the event.
     * @param from the string containing the starting date/time of the event.
     * @param to the string containing the ending date/time of the event.
     */
    public Event(String details, String from, String to) {
        super(details);
        this.from = from;
        this.to = to;
    }

    /**
     * Getter method that gets the 'from' attribute of the Event object.
     *
     * @return a string that contains the description of the 'from' attribute.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Getter method that gets the 'to' attribute of the Event object.
     *
     * @return a string that contains the description of the 'to' attribute.
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Method to print Event objects in a certain manner.
     *
     * @return a string that represents the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +   " (from: " + this.from + " to: " + this.to + ")";
    }
}
