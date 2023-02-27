package commands;

import storage.Storage;
import task.TaskParser;
import ui.TextUi;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int taskNumberInList;

    public MarkCommand(int taskNumberInList) {
        this.taskNumberInList = taskNumberInList;
    }

    @Override
    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        taskParser.markTask(ui, storage, taskNumberInList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
