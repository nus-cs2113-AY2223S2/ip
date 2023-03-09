package DukeManager.Commands;

import DukeManager.Storage.Storage;
import DukeManager.Ui.TextUi;
import DukeManager.data.TaskList;
import DukeManager.data.Tasks.Task;

import static DukeManager.common.Messages.MSG_INVALID_TASK_DISPLAYED_INDEX;

public class MarkCmd extends Cmd {
	public static final String MSG_USAGE =
			"mark/undone: marks the task identified by the index number used as done or undone.\n"
					+ "Parameters: INDEX\n"
					+ "Example: mark 1";


	public static final String MSG_MARK_TASK_SUCCESS = "Marked Task: %1$s";
	public static final String MSG_UNMARK_TASK_SUCCESS = "Unmarked Task: %1$s";
	final boolean isDone;

	public MarkCmd(int targetVisibleIndex, boolean isDone) {
		super(targetVisibleIndex);
		this.isDone = isDone;
	}


	@Override
	public void execute(TaskList tasks, TextUi ui) {
		try {
			this.taskList = tasks;
			final Task target = getTargetTask();
			TaskList.markTask(target, isDone);
			if (isDone) {
				ui.showToUser(String.format(MSG_MARK_TASK_SUCCESS, target));
			} else {
				ui.showToUser(String.format(MSG_UNMARK_TASK_SUCCESS, target));
			}
		} catch (IndexOutOfBoundsException ie) {
			ui.showToUser(MSG_INVALID_TASK_DISPLAYED_INDEX);
		}
	}
}
