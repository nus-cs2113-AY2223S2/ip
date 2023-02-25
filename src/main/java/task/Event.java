package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Event extends Task {
    public LocalDateTime from;
    public LocalDateTime to;
    public static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Event (String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to,inputFormatter);
    }

    //@Override
    public String toString() {
        String newString = super.toString().replaceFirst("T","E");
        return newString + " (from: " + from.format(printFormatter) + " to: " + to.format(printFormatter) + ")";
    }
    @Override
    public String toSaveString() {
        String newString = super.toString().replaceFirst("T", "E");
        return newString + " (from: " + from.format(inputFormatter) + " to: " + to.format(inputFormatter) + ")";
    }
}
