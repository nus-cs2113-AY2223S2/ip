package tasks;

public class Event extends Task{
    protected String startDateTime, endDateTime;

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * Class constructor with <code>description</code> and
     * <code>start</code> and <code>end</code> as
     * parameters for initialization.
     *
     * @param description the description of the task.
     * @param start the start date of the task.
     * @param end the end date of the task.
     */
    public Event(String description, String start, String end) {
        super(description, "E");
        startDateTime = start;
        endDateTime = end;
    }
    @Override
    public String toString() {
        return '[' + super.getType() + "]" + super.toString() + "(from: " + startDateTime + " to: " + endDateTime + ")";
    }

}
