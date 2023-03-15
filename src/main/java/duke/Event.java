package duke;

/**
 * A subclass Event of Task
 * Represents an event for the user containing info for the time period of the event
 */
public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Constructor to construct an event
     * @param description details of the event
     * @param from starting time
     * @param to ending time
     */
    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * gets the type of the task which is deadline
     * @return a string representing the type ([E])
     */
    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    /**
     * Returns all the information about the task
     *
     * @return a String with all the information about the task
     */
    @Override
    public String toString(){
        return getTypeIcon() + super.getStatusIcon() + super.description
                + " (from: " + this.from + " to: " + this.to + ")";
    }
}
