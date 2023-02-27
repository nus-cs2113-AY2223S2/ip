package commands;

import storage.Storage;
import task.TaskParser;
import ui.TextUi;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String findKeyword;
    public FindCommand(String findKeyword) {
        this.findKeyword = findKeyword;
    }
    @Override
    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        taskParser.findTask(ui, findKeyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
