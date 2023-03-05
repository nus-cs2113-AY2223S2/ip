package DukeManager.Commands;

import DukeManager.data.Tasks.Task;

import java.util.List;

public class ListCmd extends Cmd {

	public static final String MESSAGE_USAGE =
			"list: Displays all persons in the PersonBook as a list with index numbers.\n"
			+ "Example: list";


	@Override
	public CmdResult execute() {
		List<Task> allPersons = taskList.getAllTasks();
		return new CmdResult(getMessageForPersonListShownSummary(allPersons), allPersons);
	}
}
