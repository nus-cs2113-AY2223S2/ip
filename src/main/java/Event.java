/**
 * Represents a task with a event that the user wishes to complete.
 */

public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String Description, String start, String end) {
        super(Description);
        this.start = start;
        this.end = end;
    }

    public String getTaskIcon() {
        return "E";
    }

    public String getEventStart() {
        return start;
    }

    public String getEventEnd() {
        return end;
    }

    /**
     * Returns the Event task type and shows whether the task has been completed, as well as the duration
     * of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + start + "to" + end + ")";
    }
}
