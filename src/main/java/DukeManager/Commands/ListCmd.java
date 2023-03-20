package DukeManager.Commands;

import DukeManager.Ui.TextUi;
import DukeManager.data.DukeErrors.BlankListException;
import DukeManager.data.TaskList;


public class ListCmd extends Cmd {
	public static final String MSG_USAGE =
			"list: Displays all tasks in the taskList as a list with index numbers.\n"
			+ "Example: list";

	private static final String MSG_LIST_HEADER = "\t  These are the task(s) in your list: ";

	@Override
	public void execute(TaskList tasks, TextUi ui) throws BlankListException {
		this.taskList = tasks;
		String[] taskPrintList = new String[taskList.size() + 1];
		if (taskList.size() == 0) {
			throw new BlankListException();
		}
		taskPrintList[0] = MSG_LIST_HEADER;
		for (int i = 1; i < taskList.size() + 1; i++) {
			taskPrintList[i] = String.format("%d. %s\n", i, taskList.getTask(i - 1));
		}
		ui.showToUser(taskPrintList);
	}
}
