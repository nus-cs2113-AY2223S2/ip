package alltasks;

/**
 * This Event class represents tasks that starts and ends at a specific date and time.
 * Coffee Bot keeps track of the event tasks.
 */
public class Event extends Task {
    private String fromStart;
    private String toEnd;

    /**
     * Creates an Event object from the input command.
     *
     * @param descriptive description of input command.
     * @param fromStart start of an event indicated in the input command.
     * @param toEnd end of an event indicated in the input command.
     */
    public Event(String descriptive, String fromStart, String toEnd) {
        super(descriptive);
        this.toEnd = toEnd;
        this.fromStart = fromStart;
    }

    /**
     * Returns the status of completion of the task item
     * and the description of the input command.
     *
     * @return getStatusIcon() status of completion of task item.
     * @return description description of the input command.
     * @return fromStart start of an event indicated in the input command.
     * @return toEnd end of an event indicated in the input command.
     */

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + description + "(from: " + this.fromStart + "to: " + this.toEnd + ")" ;
    }

    //Solution below adapted from Student Oh Yi Xiu Wilson
    @Override
    public String getInfo() {
        return String.format("%s|%s|%s|%s|%s", "Event", this.isDone ? 1 : 0, this.description, this.fromStart, this.toEnd);
    }
    //End of adapted solution from Student Oh Yi Xiu Wilson
}
