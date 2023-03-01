package duke;

/**
 * Represents a Event task type. An <code>Event</code> object corresponds
 * to a <code>Task</code> object that requires additional arguments such as the
 * start and cutoff dates/times. This is indicated by the user via the
 * <code>/start</code> and <code>/end</code> command e.g.,
 * <code>/event do homework /start 01-08-2023 /end 05-08-2023</code>
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Initialises the superclass <code>Task</code> and sets the description, start
     * and end date/time.
     * 
     * @param arguments user input containing task description, start and end
     *                  date/time.
     */
    public Event(String arguments) {
        super();
        String[] argumentsArray = arguments.split("/start", 2);
        String eventDescription = argumentsArray[0].trim();
        String eventStartTime = argumentsArray[1].split("/end", 2)[0].trim();
        String eventEndTime = argumentsArray[1].split("/end", 2)[1].trim();
        super.description = eventDescription;
        this.startTime = eventStartTime;
        this.endTime = eventEndTime;
    }

    @Override
    public String formattedString() {
        String formatted = "Event:" + super.isDone + ":" + super.description + ":" + startTime + ":" + endTime;
        return formatted;
    }

    @Override
    public String toString() {
        return "[EVENT]\n" + super.toString() + " (From: " + startTime + " | To: " + endTime + ")";
    }
}
