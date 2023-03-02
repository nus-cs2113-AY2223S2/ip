import java.time.LocalDate;

public class AddDeadlineCommand extends Command{
    public AddDeadlineCommand(String userInput){
        super("add deadline");
        String originalInput = userInput.replace("deadline", " ").strip();
        originalInput = originalInput.replaceAll(" / ", "");
        originalInput = originalInput.replaceAll("/", "");

        int byBeginIndex = originalInput.indexOf("by");
        super.content = originalInput.substring(0, byBeginIndex).strip();
        try{
            super.by = LocalDate.parse(originalInput.substring(byBeginIndex).replace("by", "").trim());
        } catch(Exception e){
            UI.printDateFormatErrorComment();
        }
    }
}
