package DukeManager.Commands;

import DukeManager.Storage.Storage;
import DukeManager.Ui.TextUi;
import DukeManager.data.DukeErrors.IllegalValueException;
import DukeManager.data.TaskList;
import DukeManager.data.Tasks.Task;

public class AddCmd extends Cmd {
	public static final String MSG_USAGE = "";

	public static final String MSG_SUCCESS = "New task added: %1$s";
	public static final String MSG_DUPLICATE_PERSON = "This task already exists in the tasklist";

	private final Task toAdd;

	/**
	 * Convenience constructor using raw values.
	 *
	 * @throws IllegalValueException if any of the raw values are invalid
	 */
	public AddCmd(Task task) throws IllegalValueException {
		this.toAdd = task;
	}

	public Task getTask() {
		return toAdd;
	}

	@Override
	public CmdResult execute(TaskList tasks, TextUi ui, Storage storage) {
		try {
			taskList.addTask(toAdd);
			return new CmdResult(String.format(MSG_SUCCESS, toAdd));
		} catch (TaskList.DuplicateTaskException dpe) {
			return new CmdResult(MSG_DUPLICATE_PERSON);
		}
	}
}
