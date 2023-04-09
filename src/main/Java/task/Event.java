package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Model a class to handle event, inherit from Task class.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Build constructor for event class.
     * @param description the description of the task.
     * @param start the start date&time of the event.
     * @param end the end date&time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Method to get the raw data of the start date&time.
     * @return the start in LocalDateTime format.
     */
    public LocalDateTime getRawStart() {
        return start;
    }

    /**
     * Method to get the raw data of the end date&time.
     * @return the end in LocalDateTime format.
     */
    public LocalDateTime getRawEnd() {
        return end;
    }

    /**
     * Method to get the string representation of the start.
     * @return String start with readable format.
     */
    public String getStart() {
        String literal = nthNumber(Integer.parseInt(start.format(DateTimeFormatter.ofPattern("d"))));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(String.format("d 'of' MMMM yyyy', 'h:ma"));
        String formatted = start.format(formatter);
        String date = formatted.split(" ")[0];
        String rest = formatted.substring(formatted.indexOf(" "));
        String newFormatted = date + literal + rest;
        return newFormatted;
    }

    /**
     * Method to get the string representation of the end.
     * @return String end with readable format.
     */
    public String getEnd() {
        String literal = nthNumber(Integer.parseInt(end.format(DateTimeFormatter.ofPattern("d"))));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(String.format("d 'of' MMMM yyyy', 'h:ma"));
        String formatted = end.format(formatter);
        String date = formatted.split(" ")[0];
        String rest = formatted.substring(formatted.indexOf(" "));
        String newFormatted = date + literal + rest;
        return newFormatted;
    }

    /**
     * Method to override toString().
     * @return the string representation of the class.
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + " (from: " + getStart() +
                " to: " + getEnd() + ")";
    }
}
