package duke.commands;

import java.time.LocalDateTime;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class FindByDateCommand implements Command {
    public static final String COMMAND_WORD = "find-date";
    Parser parser = new Parser();
    private String arguments;

    public FindByDateCommand(String arguments) {
        this.arguments = arguments;
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        LocalDateTime date = parser.parseDate(arguments);
        if (date != null) {
            ui.showTaskListByDate(tasks.getTasks(), date);
        }
    }

    public boolean isExit() {
        return false;
    }
}
