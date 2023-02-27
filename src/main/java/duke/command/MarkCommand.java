package duke.command;

import duke.exceptions.CommandFormatException;
import duke.exceptions.TaskDoneException;
import duke.exceptions.TaskException;
import duke.exceptions.TaskOutOfBoundsException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
	public MarkCommand(String userInput) {
		super(userInput);
	}

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskOutOfBoundsException,
			TaskDoneException, TaskException, CommandFormatException {
		String[] messages = userInput.trim().replace("mark ", "").split(" ");
		if (messages.length < 1) {
			throw new CommandFormatException();
		} else if (!messages[0].matches("\\d+")) {
			throw new CommandFormatException();
		} else if (Integer.valueOf(messages[0]) > taskList.countTaskNumber()) {
			throw new TaskOutOfBoundsException();
		} else if (Integer.valueOf(messages[0]) <= 0) {
			throw new TaskOutOfBoundsException();
		} else if (taskList.countTaskNumber() == 0) {
			throw new TaskException();
		}

		int taskIndex = Integer.valueOf(messages[0]);

		if (taskList.findTask(taskIndex).isCompleted()) {
			throw new TaskDoneException();
		}
		ui.printTaskDoneLine();
		taskList.markAsDone(taskIndex);
		System.out.println(taskList.showTaskMessage(taskIndex - 1));
	}
}
