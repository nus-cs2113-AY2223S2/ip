package buddy.commands.actionCommands;

import buddy.messages.Messages;
import buddy.ui.Ui;
import buddy.commands.Command;
import buddy.tasks.TaskList;

public class HelpCommand extends Command {
    /**
     * Process HelpCommand by user and prints help message
     *
     * @param taskList List of tasks
     * @param input Command inputted by user
     */
    @Override
    public void executeCommand(TaskList taskList, String input) {
        System.out.println(Messages.DIVIDER);
        Ui.displayHelpMessage();
        System.out.println(Messages.DIVIDER);
    }
}
