/**
 * Represents a command to find tasks with the keyword.
 * A <code>FindCommand</code> object corresponds to a command with "find" type and targetWord.
 *  It prints <code>UI.printInputErrorComment()</code> when the target word is invalid.
 */

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
