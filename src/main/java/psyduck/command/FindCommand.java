package psyduck.command;

import psyduck.exceptions.EmptyFindException;
import psyduck.tasklist.TaskList;
import psyduck.ui.ErrorMessage;
import psyduck.ui.Ui;

/**
 * Represents the command to find all tasks related to a specific keyword.
 */
public class FindCommand extends Command {

    /**
     * Executes the command to find all the tasks related to a specific keyword.
     *
     * @param input the string input from the user.
     * @param tasks The list of task we are finding matches from.
     * @param ui The user interface that interact with the user.
     */
    @Override
    public void executeCommand(String input, TaskList tasks, Ui ui) {
        try {
            String format = parser.prepareFind(input);
            tasks.findTasks(format);
        } catch (EmptyFindException e) {
            ErrorMessage.printEmptyFindMessage();
            return;
        }
    }
}
