package duke;
import duke.exceptions.EventException;

/**
 * Represents a task that is of event type.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Sets the description, done status, from date and to date of the event.
     *
     * @param description description of the event.
     * @param isDone done status of the event.
     * @param from start date/time of the event.
     * @param to end date/time of the event.
     * @throws EventException if the end date/time field is empty.
     */
    public Event(String description, boolean isDone, String from, String to) throws EventException {
        super(description, isDone);
        this.from = from;
        this.to = to;
        if(to.length() == 0){
            throw new EventException();
        }
    }

    public String getType(){
        return "E";
    }

    public String getPeriod(){
        return " (from: " + from + " to: " + to + ")";
    }

    public String getPeriodSave(){
        return  from + " | " + to;
    }

    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "  [E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}