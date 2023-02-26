package duke.command;

import duke.exceptions.NoTaskException;
import duke.tasklist.TaskList;
import duke.ui.UI;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    @Override
    public void executor(TaskList tasks, UI ui) throws NoTaskException {
        String result = tasks.find(keyword);
        ui.printTasksFound(keyword, result);
    }
}
