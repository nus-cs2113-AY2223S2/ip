package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class FindByKeywordCommand implements Command {
    public static final String COMMAND_WORD = "find";
    private String arguments;

    public FindByKeywordCommand(String arguments) {
        this.arguments = arguments;
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.showTaskListByKeyword(tasks.getTasks(), arguments);
    }

    public boolean isExit() {
        return false;
    }
}
