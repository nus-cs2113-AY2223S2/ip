package DukeManager.Commands;

import DukeManager.Storage.Storage;
import DukeManager.Ui.TextUi;
import DukeManager.data.TaskList;

public class IncorrectCmd extends Cmd {
	public final String feedbackToUser;
	public IncorrectCmd(String feedbackToUser) {
		this.feedbackToUser = feedbackToUser;
	}

	@Override
	public void execute(TaskList tasks, TextUi ui) {
		ui.showToUser((feedbackToUser));
	}
}
