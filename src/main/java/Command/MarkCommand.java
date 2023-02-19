package Command;

import CommandUtils.InputParser;
import Entities.TaskList;
import Exceptions.DukeException;
import Exceptions.NoDescriptionException;
import Exceptions.NonPositiveNumberException;
import FileUtils.Storage;
import Output.UI;

/**
 * Command to mark a task as completed
 */
public class MarkCommand extends Command implements InputParser {
    private int taskIndex;

    /**
     * Constructor for MarkCommand Class
     * @param command mark command
     * @param input user input
     * @throws DukeException
     */
    public MarkCommand(String command, String input) throws DukeException {
        parseInput(command, input);
    }

    /**
     * Executes marking of task as completed
     * @param tasks Holds all currently added tasks
     * @param ui Responsible for printing task as marked
     * @param storage Handles the storage of marked task into database
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.printMarkedTask(tasks.markTask(taskIndex));
    }

    /**
     * Parses user input to get index of task to be marked as completed
     * @param command mark command
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
