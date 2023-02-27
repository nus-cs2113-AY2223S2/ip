package DukeManager.Commands;

import DukeManager.data.TaskList;
import DukeManager.data.Tasks.Task;

import java.util.List;
public class Cmd {
	protected TaskList taskList;

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
}
