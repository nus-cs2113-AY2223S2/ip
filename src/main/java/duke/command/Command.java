package duke.command;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public abstract class Command {
	protected String userInput;

	public Command(String userInput) {
		this.userInput = userInput;
	}

	public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
	}

	public boolean shouldExit() {
		return false;
	}
}