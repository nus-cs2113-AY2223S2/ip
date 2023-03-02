package duke.command;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represent a help command and print the help list
 */

public class HelpCommand extends Command {
	public HelpCommand(String userInput) {
		super(userInput);
	}

	/**
	 * Print help message
	 *
	 * @param taskList a TaskList object
	 * @param ui       a Ui object
	 * @throws DukeException if there is any error
	 */
	@Override
	public void execute(TaskList taskList, Ui ui) throws DukeException {
		ui.printHelp();
	}
}
