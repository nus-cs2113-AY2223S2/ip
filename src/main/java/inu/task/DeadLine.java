package inu.task;

import inu.commons.Util;

import java.time.LocalDateTime;

/**
 * Represents a deadline.
 */
public class DeadLine extends Task {
    private LocalDateTime by;

    public DeadLine(String description, LocalDateTime by) {
        super(description);
        setBy(by);
    }

    public LocalDateTime getBy() {
        return by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    @Override
    public String toString() {
        String byDate = Util.convertDateTimeToString(by);
        return String.format("[D]%s (by: %s)", super.toString(), byDate);
    }

    @Override
    public String encodeTaskToString() {
        return String.format("D//%s//%s//%s", getStatusIcon(), getDescription(), getBy());
    }
}
