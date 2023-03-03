package duke;

/**
 * This object represents a task object of Event type.
 * Deadline type contains all the attributes and methods from template Task and also contains when this task starts and ends
 */
public class Event extends Task{
    protected String start;
    protected String end;

    /**
     * Initialises a Event object that contains the description and start and time of the task
     * @param description The description of the task
     * @param start The date/time that this task should start
     * @param end The date/time that this task should end
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Overrides the template Task toString with an additional [E] string which symbolizes that it is an Event task
     * @return Returns the symbol [E], the status icon and the description and the start and end time of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Overrides the template Task getSymbol with "[E]" string which symbolizes that it is an Event task
     * @return Returns the character "E" symbolizes that it is an Event task
     */
    @Override
    public String getSymbol() {
        return "E";
    }
}
