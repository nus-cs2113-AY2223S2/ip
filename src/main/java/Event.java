/**
 * tasks that start at a specific date/time and ends at a specific date/time.
 * e.g., (a) team project meeting 2/10/2019 2-4pm.
 *      (b) orientation week 4/10/2019 to 11/10/2019.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor initializing the content, start time, end time of the Event task.
     * The task is unmarked by default.
     * @param content content of the Event.
     * @param from starting time of the Event.
     * @param to ending time of the Event.
     */
    public Event(String content, String from, String to) {
        super(content);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Converts the task to a string with label, marked status, starting and ending time.
     * @return a string containing the task's label, marked status, starting and ending time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }
}
