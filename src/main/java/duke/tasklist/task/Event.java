package duke.tasklist.task;

public class Event extends Task {
    private static final String EVENT_ICON = "E";

    private String from;
    private String to;

    /**
     * Initialises an instance of Event task.
     * Stores a task name, start date/time and end date/time into the instance of Event.
     *
     * @param taskName Name of the event task.
     * @param from Start date/time of the event task.
     * @param to End date/time of the event task.
     */
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns event task formatted as a string for printing to CLI.
     *
     * @return String format of event task for printing to CLI.
     */
    @Override
    public String toString() {
        return "[" + EVENT_ICON + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns event task formatted as a string for saving to duke.txt.
     *
     * @return String format of event task for saving to duke.txt.
     */
    @Override
    public String toFile() {
        return EVENT_ICON + " " + super.toFile() + " | " + from + " | " + to;
    }

}
