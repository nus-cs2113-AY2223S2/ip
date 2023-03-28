package Onandon.module;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Representation of the event class.
 */
public class Event extends Task {

    protected LocalDate to;
    protected LocalDate from;
    protected String dateFormat = "yyyy-MM-dd";
    protected String printDateFormat = "MMM dd yyyy";

    /**
     * Create new Event class with the specified configuration.
     *
     * @param description Description of the command.
     * @param to 'to' for the event command.
     * @param from 'from' for the event command.
     */
    public Event(String description, String to, String from) {
        super(description);
        this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern(dateFormat));
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * Get the 'to' parameter in the String.
     */
    @Override
    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * Get the 'from' parameter in the String.
     */
    @Override
    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * Print the state of the Event class.
     */
    @Override
    public String toString() {
        String printTo = to.format(DateTimeFormatter.ofPattern(printDateFormat));
        String printFrom = from.format(DateTimeFormatter.ofPattern(printDateFormat));
        return "[E]" + super.toString() + " " + description + " (from: " + printFrom + " to: " + printTo + ")";
    }
}