package Onandon.module;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);

        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HH mm"));
    }

    @Override
    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        String printBy = this.by.format(DateTimeFormatter.ofPattern("MM dd yyyy"));
        return "[D]" + super.toString() + " " + description + " (by: " + printBy + ")";
    }
}