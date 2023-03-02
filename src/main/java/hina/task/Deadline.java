package hina.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import hina.helper.Ui;

public class Deadline extends Task {
    private LocalDateTime by;
    String byString;
    public Deadline(String description, LocalDateTime by) {
        super(description);
            this.by = by;
            this.byString = this.by.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
    }

    public String toString() {
        String mark;
        if (super.isDone()) {
            mark = "X";
        } else {
            mark = " ";
        }
        return String.format("[D][%s] %s (by: %s)", mark, super.getDescription(), byString);
    }

    public String toSave() {
        return String.format("D / %s / %s / %s", isDone? "1" : "0", description, byString);
    }
}
