package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String by;
    protected LocalDate byDate;

    public String getBy() {
        return by;
    }

    public Deadline(String description, String doneBy) throws DateTimeException {
        super(description, "D");
        by = doneBy;
        byDate = LocalDate.parse(doneBy);
    }
    @Override
    public String toString() {
        return '[' + super.getType() + "]" + super.toString() + "(by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ')';
    }

}
