package commands;

import storage.Storage;
import task.TaskParser;
import ui.TextUi;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {

    }

    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        taskParser.listTasks(ui);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
