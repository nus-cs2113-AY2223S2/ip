public class DeleteCommand extends Command{
    private int targetTaskIndex;

    public DeleteCommand(String userInput){
        super("delete");
        try{
            String originalInput = userInput.replace("delete", " ").trim();
            targetTaskIndex = Integer.parseInt(originalInput);
        } catch(Exception e){
            UI.printInputErrorComment();
        }
    }

    public int getTargetTaskIndex(){
        return targetTaskIndex;
    }
}
