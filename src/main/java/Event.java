public class Event extends Task{
    protected String start;
    protected String end;

    /** Creates an event-type task, which contains a description and start and end timing */
    public Event(String description, String start, String end) {
        super(description);
        setStartEnd(start, end);
    }

    /** Sets the start and end timing of the task */
    public void setStartEnd (String start, String end) {
        this.start = start;
        this.end = end;
    }

    /** Returns type of task (event) */
    @Override
    public String getType() {
        return "event";
    }

    /** Returns start and end timing as a single string, separated by a slash */
    @Override
    public String getTimings() {
        return this.start + "/" + this.end;
    }
}
