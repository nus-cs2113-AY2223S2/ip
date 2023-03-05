package DukeManager.Commands;

import DukeManager.common.Messages;
import DukeManager.data.TaskList;
import DukeManager.data.Tasks.Task;

import java.util.List;
public class Cmd {
	protected TaskList taskList;
	private int targetIndex = -1;

	/**
	 * @param targetIndex last visible listing index of the target person
	 */
	public Cmd(int targetIndex) {
		this.setTargetIndex(targetIndex);
	}

	protected Cmd() {
	}


	/**
	 * Executes the command and returns the result.
	 */
	public CmdResult execute() {
		throw new UnsupportedOperationException("This method is to be implemented by child classes");
	};

	/**
	 * Supplies the data the command will operate on.
	 */
	public void setData(TaskList taskList) {
		this.taskList = taskList;
	}

	/**
	 * Extracts the target person in the last shown list from the given arguments.
	 *
	 * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
	 */
	protected Task getTargetTask() throws IndexOutOfBoundsException {
		return taskList.getLastShownList().get(getTargetIndex() - 1);
	}

	/**
	 * Constructs a feedback message to summarise an operation that displayed a listing of tasks.
	 *
	 * @param tasksDisplayed used to generate summary
	 * @return summary message for persons displayed
	 */
	public static String getMessageForPersonListShownSummary(List<Task> tasksDisplayed) {
		return String.format(Messages.MSG_TASKS_LISTED_OVERVIEW, tasksDisplayed.size());
	}

	public int getTargetIndex() {
		return targetIndex;
	}

	public void setTargetIndex(int targetIndex) {
		this.targetIndex = targetIndex;
	}
}
