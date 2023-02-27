package Tasks;
public class Event extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Constructor for event
     *
     * @param description string of description of event
     * @param startTime string of the start time of event
     * @param endTime string of the end time of event
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Getter for event start time
     *
     * @return return event start time
     */
    public String getFrom() {
        return startTime;
    }

    /**
     * Getter for event end time
     *
     * @return return event end time
     */
    public String getTo() {
        return endTime;
    }

    /**
     * Getter for event type
     *
     * @return return event
     */
    @Override
    public String getType() {
        return "event";
    }

    /**
     * Getter for full description of a event
     *
     * @return string with event status, description, from when to when
     */
    @Override
    public String fullDescription() {
        String fullSentence = (isDone ? "[E][X] " : "[E][ ] ") + this.description +
                "(from: " + this.startTime + " to: " + this.endTime + ")";
        return fullSentence;
    }
}
