package Onandon.module;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    protected String dateFormat = "dd,MM,yyyy HHmm";

    public Deadline(String description, String by) {
        super(description);

        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(dateFormat));
    }

    @Override
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    public LocalDateTime getByData(){
        return by;
    }

    @Override
    public String toString() {
        String printBy = by.format(DateTimeFormatter.ofPattern(dateFormat));
        return "[D]" + super.toString() + " " + description + " (by: " + printBy + ")";
    }
}