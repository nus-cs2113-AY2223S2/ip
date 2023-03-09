package DukeManager.Commands;

import DukeManager.Storage.Storage;
import DukeManager.Ui.TextUi;
import DukeManager.data.TaskList;
import DukeManager.data.Tasks.Task;

import static DukeManager.common.Messages.MSG_INVALID_TASK_DISPLAYED_INDEX;
import static DukeManager.common.Messages.MSG_TASK_NOT_IN_TASKLIST;

public class DeleteCmd extends Cmd {

	public static final String MSG_USAGE =
			"delete: Deletes the person identified by the index number used in the last person listing.\n"
			+ "Parameters: INDEX\n"
			+ "Example: delete 1";

	public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Person: %1$s";

	public DeleteCmd(int targetVisibleIndex) {
		super(targetVisibleIndex);
	}


	@Override
	public void execute(TaskList tasks, TextUi ui) {
		this.taskList = tasks;
		try {
			Task target = getTargetTask();
			taskList.removeTask(target);
			ui.showToUser(String.format(MESSAGE_DELETE_TASK_SUCCESS, target));
		//}// catch (IndexOutOfBoundsException ie) {
			//ui.showToUser(MSG_INVALID_TASK_DISPLAYED_INDEX);
		} catch (TaskList.TaskNotFoundException pnfe) {
			ui.showToUser(MSG_TASK_NOT_IN_TASKLIST);
		}
	}
}
