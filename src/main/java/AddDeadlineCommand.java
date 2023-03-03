import java.time.LocalDate;

/**
 * Represents a command to add deadline.
 * A <code>AddDeadlineCommand</code> object corresponds to a command with "add deadline" type, content, and by(due date).
 * It prints <code>UI.printDateFormatErrorComment()</code> if the input has an invalid date format.
 */

public class AddDeadlineCommand extends Command{
    public AddDeadlineCommand(String userInput){
        super("add deadline");
        String originalInput = userInput.replace("deadline", " ").strip();
        originalInput = originalInput.replaceAll(" / ", "");
        originalInput = originalInput.replaceAll("/", "");

        int byBeginIndex = originalInput.lastIndexOf("by ");
        super.content = originalInput.substring(0, byBeginIndex).strip();
        try{
            super.by = LocalDate.parse(originalInput.substring(byBeginIndex).replace("by ", "").trim());
        } catch(Exception e){
            UI.printDateFormatErrorComment();
        }
    }
}
