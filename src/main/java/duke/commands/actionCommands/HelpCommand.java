package duke.commands.actionCommands;

import duke.commands.Command;
import duke.exceptions.InvalidArgsException;
import duke.save.Storage;
import duke.tasks.TaskList;

import static duke.constants.Constants.MESSAGE_HELP;

public class HelpCommand extends Command {

    @Override
    public void handleCommand(String line, TaskList tasks, Storage storage) {
        try {
            if (getArgumentNumber(line) != 1) {
                throw new InvalidArgsException();
            }
            System.out.println(MESSAGE_HELP);
        } catch (InvalidArgsException e) {
            System.out.println(e.getMessage());
        }
    }
}
