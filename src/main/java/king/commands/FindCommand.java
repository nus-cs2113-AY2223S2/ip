package king.commands;

import king.exceptions.NoTasksException;
import king.tasks.TaskList;

public class FindCommand extends Command {
    private final String keyword;
    private final TaskList taskList;

    public FindCommand(TaskList taskList, String prompt) {
        this.taskList = taskList;
        this.keyword = prompt;
    }

    @Override
    public void handleCommand() throws NoTasksException {
        taskList.printFilteredTasks(keyword);
    }
}
