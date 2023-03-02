package duke.command;

import duke.exceptions.TaskException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a List command and list all the tasks in the task list
 */
public class ListCommand extends Command {
	public ListCommand(String userInput) {
		super(userInput);
	}

	/**
	 * Execute the command as below
	 * Check the number of the tasks
	 * Print the task list
	 *
	 * @param taskList task list
	 * @param ui       a Ui object
	 * @throws TaskException if there is no task
	 */
	@Override
	public void execute(TaskList taskList, Ui ui) throws TaskException {
		if (taskList.countTaskNumber() == 0) {
			throw new TaskException();
		}
		taskList.showTaskNumber();
		taskList.printList();
	}
}





