package duke.command;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
	public ExitCommand(String userInput) {
		super(userInput);
	}

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) {
		ui.printExitLine();
	}

	@Override
	public boolean shouldExit() {
		return true;
	}
}
