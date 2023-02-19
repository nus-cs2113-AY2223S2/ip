package commands;

import managers.OutputDialogueManager;
import managers.SaveManager;
import managers.TaskManager;

/**
 * Represents a command to display all the Tasks currently stored in list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskManager taskList, SaveManager storage, OutputDialogueManager display) {
        taskList.listAllItems(display);
    }
}
