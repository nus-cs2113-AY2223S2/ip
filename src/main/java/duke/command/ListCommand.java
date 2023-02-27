package duke.command;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.exceptions.TaskException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
	public ListCommand(String userInput) {
		super(userInput);
	}

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskException {
		if (taskList.countTaskNumber() == 0) {
			throw new TaskException();
		}
		taskList.showTaskNumber();
		taskList.printList();
	}
}





