package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList tasks, Storage database) {
        Ui.FindTaskByKeywordOpeningMessage();
        tasks.printTasksByKeyword(keyword);
        Ui.printLine();
    }

}
