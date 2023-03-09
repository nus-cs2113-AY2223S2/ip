package DukeManager.Commands;

import DukeManager.Storage.Storage;
import DukeManager.Ui.TextUi;
import DukeManager.data.TaskList;

public class ExitCmd extends Cmd {
	public static final String MSG_USAGE = "exit : Shows program usage instructions.\n"
			+ "Example: exit";

	public void execute(TaskList tasks, TextUi ui) {
		ui.showFarewellMessage();
		System.exit(0);
	}
}
