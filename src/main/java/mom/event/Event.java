package mom.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import mom.item.Item;
import mom.utils.Constants;

public class Event extends Item {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.OUTPUT_DATE_TIME_FORMAT.toString());

    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String getFromDate() {
        return this.from.toString();
    }

    public String getToDate() {
        return this.to.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
