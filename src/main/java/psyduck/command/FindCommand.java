package psyduck.command;

import psyduck.tasklist.TaskList;
import psyduck.ui.Ui;

/**
 * Represents the command to find all tasks related to a specific keyword.
 */
public class FindCommand extends Command {

    /**
     * Executes the command to find all the tasks related to a specific keyword.
     *
     * @param input A string array containing the string of the keyword provided
     * @param tasks The list of task we are finding matches from.
     * @param ui The user interface that interact with the user.
     */
    @Override
    public void executeCommand(String[] input, TaskList tasks, Ui ui) {
        tasks.findTasks(input[0]);
    }
}
