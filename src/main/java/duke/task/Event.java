package duke.task;

/**
 * Event class that keep tracks of the description, when the event starts and when it ends
 */
public class Event extends Task {
    /**
     * When the event starts
     */
    protected String eventFrom;
    /**
     * When the event ends
     */
    protected String eventTo;

    /**
     * Event Constructor that creates an event object which initialises the description, when the event starts,
     * when the event ends and the event string
     *
     * @param description describes the event that the user is referring to
     * @param eventFrom   is when the event starts
     * @param eventTo     is when the event ends
     */
    public Event(String description, String eventFrom, String eventTo) {
        super(description);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
        this.taskString = saveTaskString();
    }

    /**
     * Returns the specific Object Event task in readable format of a string to the user which contains information
     * such as the description, whether it's marked or not, and when the event starts and ends
     *
     * @return the String in a readable format referring to the Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventFrom + " to: " + eventTo + ")";
    }

    /**
     * Returns the string in the format that to be saved in the database
     *
     * @return the String in the right format of event type of task
     */
    @Override
    public String saveTaskString() {
        String saveString = new String();
        saveString += "E" + COMMA_TASK_SEPARATOR + isDone + COMMA_TASK_SEPARATOR + description + COMMA_TASK_SEPARATOR
                + eventFrom + COMMA_TASK_SEPARATOR + eventTo;
        return saveString;
    }
}
