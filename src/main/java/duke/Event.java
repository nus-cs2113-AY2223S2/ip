package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    /**
     * It formats the code depending on the time input by the user
     * @return it formats the code so that it displays like for example Oct 24 2019
     */

    @Override
    public String toString() {
        //return "[E]" + super.toString() + " (from: " + by + ")";
        LocalDate startDate = LocalDate.parse(start.trim());
        LocalDate endDate = LocalDate.parse(end.trim());
        return "[E]" + super.toString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to " + endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

