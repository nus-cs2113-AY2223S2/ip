package hina.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event on a to-do list that has start and end dates and times.
 */
public class Event extends Task {
    private LocalDateTime from;
    private String fromStr;
    private LocalDateTime to;
    private String toStr;

    /**
     * Class constructor specifying this <code>Event</code>'s <code>description</code>, start and end
     * time.
     *
     * @param description description of this <code>Event</code>.
     * @param from <code>LocalDateTime</code> object specifying the start time.
     * @param to <code>LocalDateTime</code> object specifying the end time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        this.fromStr = this.from.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
        this.toStr = this.to.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        String mark;
        if (super.isDone()) {
            mark = "X";
        } else {
            mark = " ";
        }
        return String.format("[E][%s] %s(from: %s to: %s)", mark, super.getDescription(), fromStr, toStr);
    }

    public String getFromStr() {
        return fromStr;
    }

    public String getToStr() {
        return toStr;
    }

    @Override
    public String toSave() {
        return String.format("E / %s / %s / %s / %s", isDone? "1" : "0", description, fromStr, toStr);
    }
}
