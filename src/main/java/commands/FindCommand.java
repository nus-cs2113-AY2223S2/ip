package commands;

import enums.ErrorDialogueTypes;
import errors.NoMatchingItemException;
import managers.OutputDialogueManager;
import managers.SaveManager;
import managers.TaskManager;

/**
 * Represents a command to find Tasks with names that contains a specified String.
 */
public class FindCommand extends Command {
    private String toFind;

    /**
     * Constructs a FindCommand Object from the String containing the word or phrase to find.
     *
     * @param toFind A String containing the word or phrase for this command to find.
     */
    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskManager taskList, SaveManager storage, OutputDialogueManager display) {
        try {
            taskList.findTaskWithWord(toFind, display);
        } catch (NoMatchingItemException e) {
            display.printErrorDialogue(ErrorDialogueTypes.NO_MATCHING_TASK);
            taskList.listAllItems(display);
        }
    }
}
