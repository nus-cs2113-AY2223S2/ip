import java.time.LocalDate;

/**
 * Represents a command to add event.
 * A <code>AddEventCommand</code> object corresponds to a command with "add event" type, content, from(starting date), and to(ending date).
 * It prints <code>UI.printDateFormatErrorComment()</code> if the input has an invalid date format.
 */

public class AddEventCommand extends Command{
    public AddEventCommand(String userInput){
        super("add event");
        String originalInput = userInput.replace("event", "").strip();
        originalInput = originalInput.replaceAll(" / ", "");
        originalInput = originalInput.replaceAll("/", "");

        int fromBeginIndex = originalInput.lastIndexOf("from ");
        int toBeginIndex = originalInput.lastIndexOf("to ");

        content = originalInput.substring(0, fromBeginIndex).strip();

        try{
            super.from = LocalDate.parse(originalInput.substring(fromBeginIndex, toBeginIndex).replace("from ", "").trim());
            super.to = LocalDate.parse(originalInput.substring(toBeginIndex).replace("to ", "").trim());

        } catch(Exception e){
            UI.printDateFormatErrorComment();
        }
    }
}