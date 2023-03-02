package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

import java.io.IOException;

public class Duke {

	private Storage storage;
	private TaskList tasksList;
	private final Ui ui;

	/**
	 * Constructor
	 */
	public Duke(String filePath) {
		ui = new Ui();
		storage = new Storage(filePath);
		try {
			tasksList = storage.loadTasks();
		} finally {
			System.out.println("Task loaded...");
		}
	}

	/**
	 * This part of the code is taken from the module website
	 *
	 * The code is to show welcome message and continuously loop to read in user input, until user input is "exit"
	 * Read in user command and use Parser to parse the command and create a command object
	 * Execute the command based on the user command
	 * Write the task list to file before exit the programme.
	 */
	public void startDuke() {
		ui.showWelcome();
		ui.printDivider();

		boolean shouldExit = false;
		while (!shouldExit) {
			try {
				String fullCommand = ui.readCommand();
				ui.printDivider();
				Command c = Parser.parse(fullCommand);
				c.execute(tasksList, ui);
				shouldExit = c.shouldExit();
				if (!shouldExit) {
					try {
						storage.writeToFile(tasksList);
					} catch (IOException e) {
						System.out.println("Task list updated...");
					}
				}
			} catch (DukeException e) {
				ui.showError(e.getMessage());
			} finally {
				ui.printDivider();
			}
		}
	}

	/**
	 * Create a Duke object and create / find the data file and start the Duke
	 */
	public static void main(String[] args) {
		new Duke("./data/tasks.txt").startDuke();
	}
}
