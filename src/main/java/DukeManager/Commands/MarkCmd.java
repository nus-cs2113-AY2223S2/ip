package DukeManager.Commands;

import DukeManager.data.TaskList;
import DukeManager.data.Tasks.Task;

import static DukeManager.common.Messages.MSG_INVALID_TASK_DISPLAYED_INDEX;
import static DukeManager.common.Messages.MSG_TASK_NOT_IN_TASKLIST;

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
	public CmdResult execute() {
		try {
			final Task target = getTargetTask();
			TaskList.markTask(target, isDone);
			if (isDone) {
				return new CmdResult(String.format(MSG_MARK_TASK_SUCCESS, target));
			} else {
				return new CmdResult(String.format(MSG_UNMARK_TASK_SUCCESS, target));
			}
		} catch (IndexOutOfBoundsException ie) {
			return new CmdResult(MSG_INVALID_TASK_DISPLAYED_INDEX);
		}
	}
}
