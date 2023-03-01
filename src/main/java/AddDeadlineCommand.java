public class AddDeadlineCommand extends Command{
    public AddDeadlineCommand(String userInput){
        super("add deadline");
        String originalInput = userInput.replace("deadline", " ").strip();
        originalInput = originalInput.replaceAll(" / ", "");
        originalInput = originalInput.replaceAll("/", "");

        int byBeginIndex = originalInput.indexOf("by");
        super.content = originalInput.substring(0, byBeginIndex).strip();
        super.by = originalInput.substring(byBeginIndex).replace("by", "").trim();
    }
}
