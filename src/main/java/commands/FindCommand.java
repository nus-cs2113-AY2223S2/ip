package commands;

import enums.ErrorDialogueTypes;
import errors.NoMatchingItemException;
import managers.OutputDialogueManager;
import managers.SaveManager;
import managers.TaskManager;

public class FindCommand extends Command {
    private String toFind;

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
