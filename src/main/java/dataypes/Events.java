package dataypes;
import exceptions.FromAfterTo;
import exceptions.WrongChrono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * This class extends {@link Task} by providing each {@link Task} with a range of dates
 * {@link Events#from} a certain date {@link Events#to} another date.
 *
 * @author Muthya Narayanchary Akhil
 */
public class Events extends Task implements TaskFileHandler {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * The constructor creates a new object of type {@link Events} and also prints out the data with which it was instantiated.
     *
     * @param description The description of the {@link Task}
     * @param from The {@link Events#from} date stored as a {@link LocalDate} Object
     * @param to The {@link Events#to} date stored as a {@link LocalDate} Object
     * @throws DateTimeParseException In the event the format of the from or to dates is incorrect
     * @throws FromAfterTo In the event if the from date is after the to date.
     * @throws WrongChrono In the event both the from and to dates are in the past
     */
    public Events(String description, String from, String to) throws DateTimeParseException, FromAfterTo, WrongChrono{
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
        if(this.from.isAfter(this.to)) {
            throw new FromAfterTo();
        } else if(this.from.isBefore(LocalDate.now()) || this.from.isBefore(LocalDate.now())) {
            throw new WrongChrono();
        }
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + this.getStatusAndDescription());
    }

    /**
     * An empty constructor for {@link Events}
     */
    public Events(){}

    /**
     * Returns the {@link Events#from} date in the form of a {@link String} formatted as "MMM d yyyy"
     *
     * @return A {@link String} representing the {@link Events#from} date.
     */
    public String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the {@link Events#to} date in the form of a {@link String} formatted as "MMM d yyyy"
     *
     * @return A {@link String} representing the {@link Events#to} date.
     */
    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Sets the {@link Events#from} to another date.
     *
     * @param from A {@link String} of the format "yyyy-mm-dd" which needs to be set as the {@link Events#from}
     */
    public void setFrom(String from) {
        this.from = LocalDate.parse(from);
    }

    /**
     * Sets the {@link Events#to} to another date.
     *
     * @param to A {@link String} of the format "yyyy-mm-dd" which needs to be set as the {@link Events#to}
     */
    public void setTo(String to) {
        this.to = LocalDate.parse(to);
    }

    @Override
    public String getStatusAndDescription() {
        return "[E]" + super.getStatusAndDescription() + "(from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }
    public String enCode() {
        return "E # " + super.enCode() + " # " + this.from + " # " + this.to;
    }
}
