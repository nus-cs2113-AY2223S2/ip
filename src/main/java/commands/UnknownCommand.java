package commands;

import enums.DialogueTypes;
import managers.OutputDialogueManager;
import managers.SaveManager;
import managers.TaskManager;

/**
 * Represents a command of unknown input, which is processed as an error.
 */
public class UnknownCommand extends Command {
    @Override
    public void execute(TaskManager taskList, SaveManager storage, OutputDialogueManager display) {
        display.printInteraction(DialogueTypes.UNKNOWN_TYPE);
    }
}
