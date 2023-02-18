package Command;

import CommandUtils.InputParser;
import Entities.Task;
import Entities.TaskList;
import Exceptions.DukeException;
import Exceptions.NoDescriptionException;
import Exceptions.NonPositiveNumberException;
import FileUtils.Storage;
import Output.UI;

public class DeleteCommand extends Command implements InputParser {
    private int taskIndex;

    public DeleteCommand(String command, String input) throws DukeException {
        parseInput(command, input);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        Task removedTask = tasks.deleteTask(taskIndex);
        ui.taskDeletedMessage(removedTask, tasks.getTasksCount());
        storage.write(tasks);
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
