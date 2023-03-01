package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getRawBy() {
        return by;
    }

    public String getBy() {
        String literal = nthNumber(Integer.parseInt(by.format(DateTimeFormatter.ofPattern("d"))));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(String.format("d 'of' MMMM yyyy', 'h:ma"));
        String formatted = by.format(formatter);
        String date = formatted.split(" ")[0];
        String rest = formatted.substring(formatted.indexOf(" "));
        String newFormatted = date + literal + rest;
        return newFormatted;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + " (by: " + getBy() + ")";
    }
}
