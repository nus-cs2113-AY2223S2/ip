package duke.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.item.Item;

public class Event extends Item {
    private static String format = "dd MMM yyyy hh:mm a";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
