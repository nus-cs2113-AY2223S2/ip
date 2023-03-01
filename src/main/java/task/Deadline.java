package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline class that inherits from {@link Task} with additional {@link #dueBy} field
 */
public class Deadline extends Task {
    public LocalDateTime dueBy;
    public static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    public Deadline(String description, String dueBy) throws DateTimeParseException {
        super(description);
        this.dueBy = LocalDateTime.parse(dueBy, inputFormatter);
    }

    /**
     * toString method to be called when Deadline is to be printed
     * @return The string to be printed
     */
    @Override
    public String toString() {
        String newString = super.toString().replaceFirst("T", "D");
        return newString + " (by: " + dueBy.format(printFormatter) + ")";
    }


    /**
     * This method produces the same String as {@link  #toString} except for the format of the time.
     * The format of the time in the String here is yyyy-MM-dd HH:mm. This is also the format of the String
     * that will be saved to the ./data/duke.txt file
     *
     * @return The String in the correct format to be saved into the .txt file
     */
    @Override
    public String toSaveString() {
        String newString = super.toString().replaceFirst("T", "D");
        return newString + " (by: " + dueBy.format(inputFormatter) + ")";
    }
}