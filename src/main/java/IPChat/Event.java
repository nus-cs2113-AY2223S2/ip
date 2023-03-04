package IPChat;

/**
 * Class which is used for creating tasks with events from a given time to another
 *
 * @author DeepanjaliDhawan
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor which is used to instantiate the event object
     *
     * @param description Description of the task 'event'
     * @param from The time allotted for the event to begin
     * @param to The time allotted for the event to end
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Method to return the string in the ArrayList
     * @return String in the ArrayList
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    /**
     * Method to save the event in the file
     * @return event descriptions along with from and to times
     */
    @Override
    public String saveStuff () {
        int save = 0;
        if (this.isDone) {
            save = 1;
        }
        return "event" + description + "/from" + from + " | " + "/to" + to + save;
    }
}