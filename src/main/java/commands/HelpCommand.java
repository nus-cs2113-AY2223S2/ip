package commands;

import enums.DialogueTypes;
import managers.OutputDialogueManager;
import managers.SaveManager;
import managers.TaskManager;

public class HelpCommand extends Command {
    @Override
    public void execute(TaskManager taskList, SaveManager storage, OutputDialogueManager display) {
        display.printInteraction(DialogueTypes.HELP_MENU);
    }
}
