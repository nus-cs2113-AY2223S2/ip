package duke;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

public class Event extends Task {

    protected Date from;
    protected Date to;

    public Event(String description, Date from, Date to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        final DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
        return "[E]" + super.toString() +
                " (from: " + dateFormat.format(from) + ", to: " + dateFormat.format(to) + ") ";
    }
    public String toStorage() {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return "[E]" + super.toStorage() + " from: " + dateFormat.format(from)
                + " to: " + dateFormat.format(to);
    }
}
