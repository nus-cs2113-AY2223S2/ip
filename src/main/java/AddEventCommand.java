public class AddEventCommand extends Command{
    public AddEventCommand(String userInput){
        super("add event");
        String originalInput = userInput.replace("event", "").strip();
        originalInput = originalInput.replaceAll(" / ", "");
        originalInput = originalInput.replaceAll("/", "");

        int fromBeginIndex = originalInput.indexOf("from");
        int toBeginIndex = originalInput.indexOf("to");

        content = originalInput.substring(0, fromBeginIndex).strip();
        from = originalInput.substring(fromBeginIndex, toBeginIndex).replace("from", "").trim();
        to = originalInput.substring(toBeginIndex).replace("to", "").trim();
    }
}