package commands;

import storage.Storage;
import task.TaskParser;
import ui.TextUi;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int taskNumberInList;

    public DeleteCommand(int taskNumberInList) {
        this.taskNumberInList = taskNumberInList;
    }

    @Override
    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        taskParser.deleteAndPrintTask(ui, storage, taskNumberInList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
