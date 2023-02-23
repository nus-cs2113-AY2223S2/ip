package duke.command;

import duke.exceptions.NoTaskException;
import duke.tasks.TaskList;
import duke.ui.UI;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    @Override
    public void executor(TaskList tasks, UI ui) throws NoTaskException {
        String result = tasks.find(keyword);
        ui.print("Showing matches for keyword: " + keyword + "\n");
        ui.print(result);
        ui.printLine();
    }
}
