package Command;

import CommandUtils.ParseInput;
import Entities.TaskList;
import Exceptions.DukeException;
import Exceptions.NoDescriptionException;
import Exceptions.NonPositiveNumberException;
import FileUtils.Storage;
import Output.UI;

public class UnmarkCommand extends Command implements ParseInput {
    private int taskIndex;

    public UnmarkCommand(String command, String input) throws DukeException {
        parseInput(command, input);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.printUnmarkedTask(tasks.unmarkTask(taskIndex));
    }

    public void parseInput(String command, String input) throws DukeException {
        if (input.length() == command.length()) {
            throw new NoDescriptionException(command);
        }

        try {
            this.taskIndex = Integer.parseInt(input.substring(command.length() + 1)) - 1;
        } catch (NumberFormatException e) {
            throw new NonPositiveNumberException(e);
        }
    }
}
