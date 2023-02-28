package Tasks;

import Handlers.Parser;

public class Event extends Task {

    protected String eventFrom;
    protected String eventTo;

    /**
     * Constructs event object.
     * 
     * @param description Description of the task
     * @param from Start time of the event
     * @param to End time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.eventFrom = from;
        this.eventTo = to;
    }

    /**
     * Returns the description of the task for user to read
     * 
     * @return String the description of the task
     */
    @Override
    public String describeTask() {
        return "[" + Parser.EVENT_TASK_TYPE + "]" + super.describeTask() + " (from: " + eventFrom + " to: " + eventTo + ")";
    }

    /**
     * Returns the description of the task in a format that is
     * suitable for writing to file.
     * 
     * @return String the description of the task 
     */
    @Override
    public String describeTaskForFile() {
        return Parser.EVENT_TASK_TYPE + " | " + (isDone ? "1" : "0") + " | " + description + " | " + eventFrom + "-" + eventTo;
    }
}
