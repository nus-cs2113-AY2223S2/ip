public class FindCommand extends Command {
    public FindCommand(String userInput){
        super("find");
        try{
            String originalInput = userInput.replace("find", " ").trim();
            super.targetWord = originalInput;
        } catch(Exception e){
            UI.printInputErrorComment();
        }
    }
}
