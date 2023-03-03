package duke.command;

import duke.exceptions.NoTaskException;
import duke.tasklist.TaskList;
import duke.ui.UI;

public class FindCommand extends Command {
    private final String userQuery;

    public FindCommand(String userQuery) {
        this.userQuery = userQuery.trim();
    }

    @Override
    public void executor(TaskList tasks, UI ui) throws NoTaskException {
        String result = tasks.findTasks(userQuery);
        ui.printTasksFound(userQuery, result);
    }
}
