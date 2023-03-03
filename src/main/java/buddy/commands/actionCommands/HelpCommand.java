package buddy.commands.actionCommands;

import buddy.messages.Messages;
import buddy.ui.Ui;
import buddy.commands.Command;
import buddy.tasks.*;

public class HelpCommand extends Command {
    @Override
    public void executeCommand(TaskList taskList, String input) {
        System.out.println(Messages.DIVIDER);
        Ui.displayHelpMessage();
        System.out.println(Messages.DIVIDER);
    }
}
