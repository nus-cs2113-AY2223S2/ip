package duke;

public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Initializes Event class.
     *
     * @param description Description of event.
     * @param from        Date/time when event starts.
     * @param to          Date/time when event ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }


    /**
     * Returns standardized string to be stored on memory that can be read back into
     * the program in the future.
     *
     * @return Standardized string to be stored in memory.
     */
    public String storeString() {
        return super.storeString() + "|E|" + description + "|" + from + "|" + to + "\n";
    }

    @Override
    /**
     * Overrides matchesKeyword in Task. Searches through description, from, and to
     * fields for a keyword.
     *
     * @param keyword Keyword to search for.
     * @return True if Event matches the keyword, False otherwise.
     */
    public boolean matchesKeyword(String keyword) {
        if (description.contains(keyword) || from.contains(keyword) || to.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    /**
     * Overrides string representation for todos, with a [E] to indicate event class
     * and conveying information about time.
     *
     * @return String Representation.
     */
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}