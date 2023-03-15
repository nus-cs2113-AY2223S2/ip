package duke.command;

import duke.exceptions.CommandFormatException;
import duke.exceptions.TaskMatchException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a find command
 * Finds and displays the tasks according to the keywords
 */
public class FindCommand extends Command {
    public FindCommand(String userInput) {
        super(userInput);
    }

    /**
     * Execute the command as below
     * Check the command whether it is correct
     * Find the tasks that matches the keywords in the command
     *
     * @param taskList a TaskList object
     * @param ui       a Ui object
     * @throws CommandFormatException if the command format is wrong
     * @throws TaskMatchException     if there is no mask matching the keyword
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws CommandFormatException, TaskMatchException {
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
