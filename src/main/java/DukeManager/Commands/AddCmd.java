package DukeManager.Commands;

import DukeManager.data.DukeErrors.IllegalValueException;
import DukeManager.data.TaskList;
import DukeManager.data.Tasks.Task;

public class AddCmd extends Cmd {
	public static final String COMMAND_WORD = "add";

	public static final String MESSAGE_USAGE = "";

	public static final String MESSAGE_SUCCESS = "New task added: %1$s";
	public static final String MESSAGE_DUPLICATE_PERSON = "This task already exists in the tasklist";

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
	public CmdResult execute() {
		try {
			taskList.addTask(toAdd);
			return new CmdResult(String.format(MESSAGE_SUCCESS, toAdd));
		} catch (TaskList.DuplicateTaskException dpe) {
			return new CmdResult(MESSAGE_DUPLICATE_PERSON);
		}
	}
}
