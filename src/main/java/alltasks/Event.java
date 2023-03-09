package alltasks;

/**
 * This Event class represents tasks that starts and ends at a specific date and time.
 * Coffee Bot keeps track of the event tasks.
 */
public class Event extends Task{
    private String from;
    private String to;

    /**
     * Creates an Event class from the input command.
     *
     * @param descriptive description of input command.
     * @param from start of an event indicated in the input command.
     * @param to end of an event indicated in the input command.
     */
    public Event(String descriptive, String from, String to) {
        super(descriptive);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the status of completion of the task item
     * and the description of the input command.
     *
     * @return getStatusIcon() status of completion of task item.
     * @return description description of the input command.
     * @return from start of an event indicated in the input command.
     * @return to end of an event indicated in the input command.
     */

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "]" + " " + description + "(from: " + this.from + "to: " + this.to + ")" ;
    }

    @Override
    public String getInfo() {
        return String.format("%s|%s|%s|%s|%s", "Event", this.isDone ? 1 : 0, this.description, this.from, this.to);
    }
}
