package Onandon.module;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representation of the deadline class.
 */
public class Deadline extends Task {
    protected LocalDate by;
    protected String dateFormat = "yyyy-MM-dd";
    protected String printDateFormat = "MMM dd yyyy";

    /**
     * Create new Deadline class with the specified configuration.
     *
     * @param description Description of the command.
     * @param by 'by' for the deadline command.
     */
    public Deadline(String description, String by) {
        super(description);

        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * Get the 'by' parameter in the String.
     */
    @Override
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * Get the 'by' parameter in the LocalDate class.
     */
    public LocalDate getByData(){
        return by;
    }

    /**
     * Print the state of the Deadline class.
     */
    @Override
    public String toString() {
        String printBy = by.format(DateTimeFormatter.ofPattern(printDateFormat));
        return "[D]" + super.toString() + " " + description + " (by: " + printBy + ")";
    }
}