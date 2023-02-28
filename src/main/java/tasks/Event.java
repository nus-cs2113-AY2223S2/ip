package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event object is a Task object that has a "from" date and a "to" date.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setFrom(String from) {
        this.from = LocalDate.parse(from.trim());
    }

    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setTo(String to) {
        this.to = LocalDate.parse(to.trim());
    }

    /** Upon creating the event, the from and to dates will be concatenated to the displayed description.
     * @param description The description of the event.
     * @param from The date this event begins.
     * @param to The date this event ends.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        setFrom(from);
        setTo(to);
        setFormattedDescription(description + "(from: " + getFrom() + " > to: " + getTo() + ')');
    }
}
