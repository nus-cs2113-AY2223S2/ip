/**
 * Represents a command to delete a task.
 * A <code>DeleteCommand</code> object corresponds to a command with "delete" type and an index of the target task.
 * It prints <code>UI.printInputErrorComment</code> if the input does not have proper target task index.
 */

public class DeleteCommand extends Command{

    public DeleteCommand(String userInput){
        super("delete");
        try{
            String originalInput = userInput.replace("delete", " ").trim();
            super.targetTaskIndex = Integer.parseInt(originalInput);
        } catch(Exception e){
            UI.printInputErrorComment();
        }
    }
}
