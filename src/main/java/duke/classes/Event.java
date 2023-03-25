package duke.classes;

/**
 * Represents an event task.
 * Inherits from Task class.
 */
public class Event extends Task {

    /** Start time of the event */
    protected String from;

    /** End time of the event */
    protected String by;

    /**
     * Constructor for Event class.
     *
     * @param description Description of the task.
     * @param from Start time of the event.
     * @param by End time of the event.
     */
    public Event(String description, String from, String by) {
        super(description);
        this.from = from;
        this.by = by;
    }

    /**
     * Returns a formatted string representation of the task.
     * Overrides the toString() method in the Task class.
     *
     * @return Formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] "+ super.toString() + " (" + from + "," + by + ")";
    }
}
