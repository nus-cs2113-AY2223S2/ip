package Onandon.module;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Class for the 'deadline' command.
public class Deadline extends Task {
    protected LocalDate by;
    protected String dateFormat = "yyyy-MM-dd";
    protected String printDateFormat = "MMM dd yyyy";

    public Deadline(String description, String by) {
        super(description);

        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern(dateFormat));
    }

    @Override
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    public LocalDate getByData(){
        return by;
    }

    @Override
    public String toString() {
        String printBy = by.format(DateTimeFormatter.ofPattern(printDateFormat));
        return "[D]" + super.toString() + " " + description + " (by: " + printBy + ")";
    }
}