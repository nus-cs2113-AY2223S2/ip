public class UnmarkCommand extends Command{

    public UnmarkCommand(String userInput){
        super("unmark");
        try{
            String originalInput = userInput.replace("unmark", " ").trim();
            super.targetTaskIndex = Integer.parseInt(originalInput);
        } catch(Exception e){
            UI.printInputErrorComment();
        }
    }
}
