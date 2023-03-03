package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected String start, end;
    protected LocalDate startDate, endDate;

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public Event(String description, String start, String end) throws DateTimeException {
        super(description, "E");
        this.start = start;
        this.end = end;
        startDate = LocalDate.parse(start);
        endDate = LocalDate.parse(end);
    }
    @Override
    public String toString() {
        return '[' + super.getType() + "]" + super.toString() + "(from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
