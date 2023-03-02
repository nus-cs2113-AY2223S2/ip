import java.time.LocalDate;

public class AddEventCommand extends Command{
    public AddEventCommand(String userInput){
        super("add event");
        String originalInput = userInput.replace("event", "").strip();
        originalInput = originalInput.replaceAll(" / ", "");
        originalInput = originalInput.replaceAll("/", "");

        int fromBeginIndex = originalInput.indexOf("from");
        int toBeginIndex = originalInput.indexOf("to");

        content = originalInput.substring(0, fromBeginIndex).strip();

        try{
            super.from = LocalDate.parse(originalInput.substring(fromBeginIndex, toBeginIndex).replace("from", "").trim());
            super.to = LocalDate.parse(originalInput.substring(toBeginIndex).replace("to", "").trim());

        } catch(Exception e){
            UI.printDateFormatErrorComment();
        }
    }
}