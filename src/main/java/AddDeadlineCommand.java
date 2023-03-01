public class AddDeadlineCommand extends Command{
    public AddDeadlineCommand(String userInput){
        super("add deadline");
        String originalInput = userInput.replace("deadline", " ").strip();

        int byBeginIndex = originalInput.indexOf("by");
        super.content = originalInput.substring(0, byBeginIndex);
        super.by = originalInput.substring(byBeginIndex).replace("by", " ").trim();
    }
}
