package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Model a class to handle deadline, inherit from Task class.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Build constructor for Deadline class.
     * @param description the description of the task.
     * @param by the deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Method to get the raw data of the deadline.
     * @return the deadline in LocalDateTime format.
     */
    public LocalDateTime getRawBy() {
        return by;
    }

    /**
     * Method to get the string representation of the deadline.
     * @return String deadline with readable format.
     */
    public String getBy() {
        String literal = nthNumber(Integer.parseInt(by.format(DateTimeFormatter.ofPattern("d"))));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(String.format("d 'of' MMMM yyyy', 'h:ma"));
        String formatted = by.format(formatter);
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
        return "[D][" + getStatusIcon() + "] " + getDescription() + " (by: " + getBy() + ")";
    }
}
