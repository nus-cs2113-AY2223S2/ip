package task;

/**
 * Handles event task.
 */
public class Event extends Task {
    private String time;

    /**
     * A constructor that accepts the description and the /to and /from time specified by the user.
     *
     * @param description The description of the deadline.
     * @param time        the /to and /from of the event.
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Set the toString parameter to print out a readable string.
     *
     * @return The readable string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + time + ")";
    }

    /**
     * Formats the string into a savable string.
     *
     * @return A string that can be saved into a file.
     */
    @Override
    public String savableString() {
        return "E|" + super.savableString() + "|" + time;
    }
}
