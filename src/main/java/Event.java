/**
 * Represents a task which has a
 * start and end time that is
 * represented by Event
 */
public class Event extends Task {
     String beg, end;

    /**
     * Creates an Event object which is inherited by
     * Task object, which then assigns the start, end
     * and description of the task
     * @param beg Start day/time of task
     * @param end End day/time of task
     * @param description Description of
     *                    event task
     */
    public Event(String beg, String end, String description) {
        super(description);
        this.beg = beg;
        this.end = end;
    }

    /**
     * Forms a string based on the data input
     * by user
     * @return Returns a string that indicates
     * the event along with start and end
     * day/time
     */
    public String toString() {
        return ("[E]" + super.toString() + "(from: " + beg + "to: " + end + ")");
    }
}