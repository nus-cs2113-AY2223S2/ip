package dataypes;
import exceptions.WrongChrono;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class extends {@link Task} by providing each {@link Task} with a {@link Deadlines#deadline}.
 *
 * @author Muthya Narayanchary Akhil
 */
public class Deadlines extends Task implements TaskFileHandler {
    protected LocalDate deadline;
    //protected LocalDateTime deadlineTesting;

    /**
     * The constructor creates a new object of type {@link Deadlines}.
     * This also prints out the content of the {@link Task} which was just instantiated.
     *
     * @param description A description of the {@link Task}.
     * @param deadline A deadline for the {@link Task} in the form of "yyyy-mm-dd".
     * @throws DateTimeParseException In the event the format of the deadline is wrong.
     * @throws WrongChrono In the event the deadline is in the past.
     */
    public Deadlines(String description, String deadline) throws DateTimeParseException, WrongChrono {
        super(description);
        this.deadline = LocalDate.parse(deadline);
        if(this.deadline.isBefore(LocalDate.now())) {
            throw new WrongChrono();
        }
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + this.getStatusAndDescription());
    }

    /**
     * An empty constructor for {@link Deadlines}
     */
    public Deadlines(){} //more when you get to decode and less in enCode

    /**
     * Returns the {@link Deadlines#deadline} in the form of a {@link String} and formatted as "MMM d yyyy";
     *
     * @return A {@link String} containing a formatted {@link Deadlines#deadline}
     */
    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Sets the {@link Deadlines#deadline} by parsing a {@link String} using {@link LocalDate#parse(CharSequence)} and
     * converting it to {@link LocalDate} type.
     *
     * @param deadline A {@link String} of the form "yyyy-mm-dd".
     */
    public void setDeadline(String deadline) {
        this.deadline = LocalDate.parse(deadline);
    }
    @Override
    public String getStatusAndDescription() {
        return "[D]" + super.getStatusAndDescription() + "(by: " + this.getDeadline() + ")";
    }
    public String enCode() {
        return "D # " + super.enCode() + " # " + this.deadline;
    }
}
