package DukeManager.Commands;

import DukeManager.Storage.Storage;
import DukeManager.Ui.TextUi;
import DukeManager.data.TaskList;

public class HelpCmd extends Cmd {

	public static final String MSG_USAGE = "help : Shows program usage instructions.\n"
			+ "Example: help";

	@Override
	public void execute(TaskList tasks, TextUi ui) {
		ui.showToUser(AddCmd.MSG_USAGE_TODO + "\n" +
				AddCmd.MSG_USAGE_DEADLINE + "\n" +
				AddCmd.MSG_USAGE_EVENT + "\n" +
				DeleteCmd.MSG_USAGE + "\n" +
				ListCmd.MSG_USAGE + "\n" +
				HelpCmd.MSG_USAGE + "\n" +
				FindCmd.MSG_USAGE + "\n" +
				ExitCmd.MSG_USAGE
		);
	}
}
