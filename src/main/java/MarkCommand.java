public class MarkCommand extends Command{
    public MarkCommand(String userInput){
        super("mark");
        try{
            String originalInput = userInput.replace("mark", " ").trim();
            super.targetTaskIndex = Integer.parseInt(originalInput);
        } catch(Exception e){
            UI.printInputErrorComment();
        }
    }
}
