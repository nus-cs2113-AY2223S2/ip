package DukeManager.Commands;

import DukeManager.Storage.Storage;
import DukeManager.Ui.TextUi;
import DukeManager.data.DukeErrors.IllegalValueException;
import DukeManager.data.TaskList;
import DukeManager.data.Tasks.Task;

public class AddCmd extends Cmd {
	public static final String MSG_SUCCESS = "New task added: %1$s";
	public static final String MSG_DUPLICATE_PERSON = "This task already exists in the tasklist";
	public static final String MSG_USAGE_TODO = "todo : Adds a simple task.\n" +
			"\t  Example: todo mop the floor";

	public static final String MSG_USAGE_DEADLINE = "deadline : Adds a task with a deadline to the list.\n" +
			"\t  Example: deadline mop the floor /by 2359";

	public static final String MSG_USAGE_EVENT = "event : Adds a task with a start time and end time to the list.\n" +
			"\t  Example: event mop the floor /from September 15 8am /to 2pm";
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
	public void execute(TaskList tasks, TextUi ui) {
		this.taskList = tasks;
		try {
			taskList.addTask(toAdd);
			ui.showToUser(String.format(MSG_SUCCESS, toAdd));
		} catch (TaskList.DuplicateTaskException dpe) {
			ui.showToUser(MSG_DUPLICATE_PERSON);
		}
	}
}
