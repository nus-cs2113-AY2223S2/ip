/**
 * Represents a command to mark a task as done.
 * A <code>MarkCommand</code> object corresponds to a command with "mark" type and an index of the target task.
 * It prints <code>UI.printInputErrorComment</code> if the input does not have proper target task index.
 */

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
