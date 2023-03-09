package DukeManager.Commands;

import DukeManager.Ui.TextUi;
import DukeManager.data.DukeErrors.BlankListException;
import DukeManager.data.TaskList;
import DukeManager.data.Tasks.Task;

public class Cmd {
	protected TaskList taskList;
	private int targetIndex = -1;
	private Task toAdd;

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
	public void execute(TaskList tasks, TextUi ui) throws BlankListException {
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
		return taskList.getAllTasks().get(getTargetIndex() - 1);
	}

	public int getTargetIndex() {
		return targetIndex;
	}
	public Task getTask() {
		return toAdd;
	}

	public void setTargetIndex(int targetIndex) {
		this.targetIndex = targetIndex;
	}
}
