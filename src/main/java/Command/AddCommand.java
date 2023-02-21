package Command;

import CommandUtils.InputParser;
import Entities.Task;
import Entities.TaskList;
import Exceptions.DukeException;
import FileUtils.Storage;
import Output.UI;

/**
 * The AddCommand class parses command to create a new task and creates the task in the constructor
 * Updates storage when execute method is called
 */
public abstract class AddCommand extends Command implements InputParser {
    protected Task addedTask;

    /**
     * Constructor of AddCommand
     * Calls parseInput method to create the task
     * @param command type of task
     * @param input input of user
     * @throws DukeException
     */
    public AddCommand(String command, String input) throws DukeException {
        parseInput(command, input);
    }

    /**
     * Adds new task to tasklist
     * Outputs task added message
     * Writes task to storage
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        tasks.addTask(addedTask);
        ui.taskAddedMessage(addedTask, tasks.getTasksCount());
        storage.write(tasks);
    }

    /**
     * Helper function to parse input string
     * To be implemented by child classes
     * @param command type of task
     * @param input input of user
     * @throws DukeException
     */
    public abstract void parseInput(String command, String input) throws DukeException;
}
