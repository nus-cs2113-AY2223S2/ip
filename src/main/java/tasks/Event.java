package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The <code>Event</code> class contains key variables that
 * is present in <code>Task</code> class with the addition of
 * <code>start</code>, <code>end</code>, <code>startDate</code>,
 * <code>endDate</code> variables. The first two stores the
 * <code>String</code> of the start and end dates while the
 * other two are stored in date format.
 * <p></p>
 * There are also methods that could modify and retrieve the
 * task variables present in the class.
 */
public class Event extends Task{
    protected String start, end;
    protected LocalDate startDate, endDate;

    /**
     * Class constructor with <code>description</code> and
     * <code>start</code> and <code>end</code> as
     * parameters for initialization.
     *
     * @param description the description of the task.
     * @param start the start date of the task.
     * @param end the end date of the task.
     * @throws DateTimeException if the date is given in the wrong format.
     */
    public Event(String description, String start, String end) throws DateTimeException {
        super(description, "E");
        this.start = start;
        this.end = end;
        startDate = LocalDate.parse(start);
        endDate = LocalDate.parse(end);
    }
    public String getStart() {
        return start;
    }
    public String getEnd() {
        return end;
    }
    @Override
    public String toString() {
        return '[' + super.getType() + "]" + super.toString() + "(from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
