package duke.command;

import duke.exceptions.CommandFormatException;
import duke.exceptions.TaskException;
import duke.exceptions.TaskOutOfBoundsException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
	public DeleteCommand(String userInput) {
		super(userInput);
	}

	public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandFormatException,
			TaskOutOfBoundsException, TaskException {

		String[] messages = userInput.trim().split(" ");
		if (messages.length < 2) {
			throw new CommandFormatException();
		} else if (!messages[1].matches("\\d+")) {
			throw new CommandFormatException();
		}

		int taskIndex = Integer.valueOf(messages[1]);

		if (taskIndex <= 0) {
			throw new TaskOutOfBoundsException();
		} else if (taskIndex > taskList.countTaskNumber()) {
			throw new TaskOutOfBoundsException();
		} else if (taskList.countTaskNumber() == 0) {
			throw new TaskException();
		} else {
			ui.printDeletingLine();
			taskList.findTask(taskIndex).showTask();
			taskList.deleteTask(taskIndex);
		}
	}
}

