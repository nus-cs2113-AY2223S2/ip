package inu.task;

import inu.commons.Messages;
import inu.commons.Ui;
import inu.commons.Util;
import inu.exceptionhandling.EmptyUserInputException;
import inu.exceptionhandling.ExceptionManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        String byDate = Util.convertDateToString(by);
        return String.format("[D]%s (by: %s)", super.toString(), byDate);
    }

    @Override
    public String encodeTask() {
        return "D" + "//" + getStatusIcon() + "//" + getDescription() + "//" + getBy();
    }

}
