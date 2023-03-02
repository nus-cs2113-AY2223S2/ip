package hina.task;

import hina.helper.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private String fromStr;
    private LocalDateTime to;
    private String toStr;
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        this.fromStr = this.from.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
        this.toStr = this.to.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
    }

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

    public String toSave() {
        return String.format("E / %s / %s / %s / %s", isDone? "1" : "0", description, fromStr, toStr);
    }
}
