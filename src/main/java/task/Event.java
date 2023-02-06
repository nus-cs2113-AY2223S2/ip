package task;

public class Event extends Task {

    protected String timeStart;
    protected String timeEnd;

    /**
     * Constructor for Event
     * Task with start and end time.
     * @param description
     * @param taskNumber
     * @param timeStart
     * @param timeEnd
     */
    public Event(String description, int taskNumber, String timeStart, String timeEnd) {
        super(description, taskNumber);
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        String eventTime = String.format(" from %s to %s", this.timeStart, this.timeEnd);
        return "[E]" + super.toString() + eventTime;
    }
}
