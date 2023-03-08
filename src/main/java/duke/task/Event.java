package duke.task;

/**
 * Task object with additional start and end time parameters.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs an event task with the given task name, start time and end time.
     *
     * @param taskName Name of the deadline task.
     * @param start    Time that the event starts at.
     * @param end      Time that the event ends at.
     */
    public Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    /**
     * Converts the event task into its string representation which contains its task type, completion status,
     * task name, and its start and end times.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
