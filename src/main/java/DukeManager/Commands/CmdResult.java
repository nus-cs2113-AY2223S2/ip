package DukeManager.Commands;

import DukeManager.Storage.Storage;
import DukeManager.Ui.TextUi;
import DukeManager.data.TaskList;
import DukeManager.data.Tasks.Task;

import java.util.List;

public class CmdResult {
	/** The feedback message to be shown to the user. Contains a description of the execution result */
	public final String feedbackToUser;

	/** The list of persons that was produced by the command */
	private final List<Task> relevantTasks;
	private Storage storage;
	private TaskList taskList;
	private TextUi ui;

	public CmdResult(String feedbackToUser) {
		this.feedbackToUser = feedbackToUser;
		relevantTasks = null;
	}

	public CmdResult(String feedbackToUser, List<Task> relevantPersons) {
		this.feedbackToUser = feedbackToUser;
		this.relevantTasks = relevantPersons;
	}

	/**
	 * Returns a list of persons that was produced by the command, if any.
	 */
	public List<Task> getRelevantTasks() {
		return relevantTasks;
	}
}
