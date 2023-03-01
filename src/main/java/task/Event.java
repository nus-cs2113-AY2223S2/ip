package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline class that inherits from {@link Task} with additional {@link #from} and {@link #to} field.
 */
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

    /**
     * toString method to be called when Deadline is to be printed.
     * @return The string to be printed.
     */
    //@Override
    public String toString() {
        String newString = super.toString().replaceFirst("T","E");
        return newString + " (from: " + from.format(printFormatter) + " to: " + to.format(printFormatter) + ")";
    }

    /**
     * This method produces the same String as {@link  #toString} except for the format of the time.
     * The format of the time in the String here is yyyy-MM-dd HH:mm. This is also the format of the String
     * that will be saved to the ./data/duke.txt file.
     *
     * @return The String in the correct format to be saved into the .txt file.
     */
    @Override
    public String toSaveString() {
        String newString = super.toString().replaceFirst("T", "E");
        return newString + " (from: " + from.format(inputFormatter) + " to: " + to.format(inputFormatter) + ")";
    }
}
