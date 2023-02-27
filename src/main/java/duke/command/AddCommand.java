package duke.command;

import duke.exceptions.CommandFormatException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

public class AddCommand extends Command {
	public AddCommand(String userInput) {
		super(userInput);
	}

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandFormatException {

		if (userInput.trim().split(" ").length == 1) {
			throw new CommandFormatException();
		}
		if (userInput.startsWith("todo")) {
			String taskDescription = userInput.trim().split(" ", 2)[1];
			ui.printAddingLine();
			taskList.addTodo(taskDescription, false);
		} else if (userInput.startsWith("deadline")) {
			String thisInput = userInput.replace("deadline ", "");
			if (!thisInput.contains("/by")) {
				throw new CommandFormatException();
			} else if (thisInput.split(" /by ").length < 2) {
				throw new CommandFormatException();
			} else {
				String[] messages = thisInput.split(" /by ");
				String taskDescription = messages[0];
				String date = messages[1];
				ui.printAddingLine();
				taskList.addDeadline(taskDescription, date, false);
			}
		} else if (userInput.startsWith("event")) {
			String thisInput = userInput.replace("event ", "");
			if (!thisInput.contains("/from") || !thisInput.contains("/to")) {
				throw new CommandFormatException();
			} else {
				String[] messages = thisInput.split(" /from ");
				String taskDescription = messages[0];
				String from = messages[1].split(" /to ")[0];
				String to = messages[1].split(" /to ")[1];
				ui.printAddingLine();
				taskList.addEvent(taskDescription, from, to, false);
			}
		} else {
			throw new CommandFormatException();
		}
	}
}
