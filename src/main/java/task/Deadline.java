package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public LocalDateTime dueBy;
    public static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    public Deadline(String description, String dueBy) throws DateTimeParseException {
        super(description);
        this.dueBy = LocalDateTime.parse(dueBy, inputFormatter);
    }

    //@Override
    public String toString() {
        String newString = super.toString().replaceFirst("T", "D");
        return newString + " (by: " + dueBy.format(printFormatter) + ")";
    }

    @Override
    public String toSaveString() {
        String newString = super.toString().replaceFirst("T", "D");
        return newString + " (by: " + dueBy.format(inputFormatter) + ")";
    }
}