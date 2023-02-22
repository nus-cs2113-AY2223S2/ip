package inu.task;

import inu.commons.Util;

import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDateTime from;

    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        setFrom(from);
        setTo(to);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    @Override
    public String toString() {
        String fromDate = Util.convertDateTimeToString(from);
        String toDate = Util.convertDateTimeToString(to);
        return String.format("[E]%s (from: %s || to: %s)", super.toString(), fromDate, toDate);
    }

    @Override
    public String encodeTask() {
        return "E" + "//" + getStatusIcon() + "//" + getDescription() + "//" + getFrom() + "//" + getTo();
    }

}
