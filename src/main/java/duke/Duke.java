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
	 * This method startDuke will be looping continuously until shouldExit is false:
	 * Show welcome messages, read commands from the terminal and parse the command and run the command.
	 * Write the taskList to storage and prints
	 * parse the output through the Parser to get the command to run
	 * and run the command. It then writes new taskList to storage if there are any changes
	 * Exit the loop if the command is "Exit"
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
				c.execute(tasksList, ui, storage);
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

	public static void main(String[] args) {
		new Duke("./data/tasks.txt").startDuke();
	}
}
