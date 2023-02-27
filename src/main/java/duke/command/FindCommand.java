package duke.command;

import duke.exceptions.CommandFormatException;
import duke.exceptions.TaskMatchException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
	public FindCommand(String userInput) {
		super(userInput);
	}

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandFormatException, TaskMatchException {
		String[] messages = userInput.split(" ");
		if (messages.length < 2) {
			throw new CommandFormatException();
		}

		List<Task> targetList = taskList.tasks.stream()
				.filter(s -> s.getDescription().contains(messages[1]))
				.collect(Collectors.toList());

		if (targetList.isEmpty()) {
			throw new TaskMatchException();
		}
		ui.printFindLine();
		int serialNo = 1;
		for (Task task : targetList) {
			System.out.print(serialNo + ". ");
			System.out.println(task.showTask());
			serialNo += 1;
		}
	}
}