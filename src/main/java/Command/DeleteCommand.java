package Command;

import CommandUtils.InputParser;
import Entities.Task;
import Entities.TaskList;
import Exceptions.DukeException;
import Exceptions.NoDescriptionException;
import Exceptions.NonPositiveNumberException;
import FileUtils.Storage;
import Output.UI;

/**
 * Command to delete task from tasklist
 */
public class DeleteCommand extends Command implements InputParser {
    private int taskIndex;

    /**
     * Constructor for DeleteCommand class
     * @param command delete command
     * @param input user input
     * @throws DukeException
     */
    public DeleteCommand(String command, String input) throws DukeException {
        parseInput(command, input);
    }

    /**
     * Executes the deletion of task from tasklist
     * @param tasks Handles removal of task from task list
     * @param ui Responsible for printing deletion status
     * @param storage Handles the removal of task from database
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        Task removedTask = tasks.deleteTask(taskIndex);
        ui.taskDeletedMessage(removedTask, tasks.getTasksCount());
        storage.write(tasks);
    }

    /**
     * Parses user input to get index of task to be deleted
     * @param command delete command
     * @param input user input
     * @throws DukeException
     */
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
