package duke;

/**
 * Represents an event. A <code>Event</code> object corresponds to
 * a task with a concrete start and end date.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Initializes Event class.
     *
     * @param description Description of event.
<<<<<<< HEAD
     * @param from        Date/time when event starts.
     * @param to          Date/time when event ends.
=======
     * @param from Date/time when event starts.
     * @param to Date/time when event ends.
>>>>>>> 265111f0883b68d2f2ae230b59ffbbeec3e62601
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

<<<<<<< HEAD

    /**
     * Returns standardized string to be stored on memory that can be read back into
     * the program in the future.
=======
    /**
     * Returns standardized string to be stored on memory that can be read back into the program in the future.
>>>>>>> 265111f0883b68d2f2ae230b59ffbbeec3e62601
     *
     * @return Standardized string to be stored in memory.
     */
    public String storeString() {
        return super.storeString() + "|E|" + description + "|" + from + "|" + to + "\n";
    }

    @Override
    /**
<<<<<<< HEAD
     * Overrides matchesKeyword in Task. Searches through description, from, and to
     * fields for a keyword.
=======
     * Overrides matchesKeyword in Task. Searches through description, from, and to fields for a keyword.
>>>>>>> 265111f0883b68d2f2ae230b59ffbbeec3e62601
     *
     * @param keyword Keyword to search for.
     * @return True if Event matches the keyword, False otherwise.
     */
<<<<<<< HEAD
    public boolean matchesKeyword(String keyword) {
=======
    public boolean matchesKeyword (String keyword) {
>>>>>>> 265111f0883b68d2f2ae230b59ffbbeec3e62601
        if (description.contains(keyword) || from.contains(keyword) || to.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    /**
<<<<<<< HEAD
     * Overrides string representation for todos, with a [E] to indicate event class
     * and conveying information about time.
=======
     * Overrides string representation for todos, with a [E] to indicate event class and conveying information about time.
>>>>>>> 265111f0883b68d2f2ae230b59ffbbeec3e62601
     *
     * @return String Representation.
     */
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}