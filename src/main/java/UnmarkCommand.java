/**
 * Represents a command to mark a task as undone.
 * A <code>UnmarkCommand</code> object corresponds to a command with "unmark" type and an index of the target task.
 * It prints <code>UI.printInputErrorComment</code> if the input does not have proper target task index.
 */

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
