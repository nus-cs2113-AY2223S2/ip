package app.commands;

import app.exceptions.DukeException;
import app.exceptions.IncompleteCommandException;
import app.save.Storage;
import app.tasks.Task;
import app.tasks.TaskList;
import app.ui.Ui;

import java.util.ArrayList;

/**
 * Class to find a command with description matching keyword entered by user.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor class to find the matching commands
     * @param commandDescriptor User input minus the first word.
     * @throws DukeException If error in parsing the user input.
     */
    public FindCommand(String commandDescriptor) throws DukeException {
        parseInput(commandDescriptor);
    }

    /**
     * Method to parse the user input and get the keyword.
     * @param commandDescriptor User input minus the first word.
     * @throws DukeException If command is incomplete.
     */
    private void parseInput(String commandDescriptor) throws DukeException {
        if (commandDescriptor.length() == 0) {
            throw new IncompleteCommandException();
        }
        this.keyword = commandDescriptor;
    }


    /**
     * Method to execute the FindCommand command. Initialises an empty ArrayList
     * to consolidate all tasks with description that contains the keyword.
     * @param tasks Task-list containing the existing tasks.
     * @param ui User interface to print message.
     * @param storage Saving of tasks to memory.
     * @throws DukeException If error occurs in adding filtered tasks to new ArrayList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasksWithKeyword = tasks.getTasksWithKeyword(tasks, keyword);
        ui.printTasksWithKeyword(tasksWithKeyword);
    }
}
