package commands;

import storage.Storage;
import task.TaskParser;
import ui.TextUi;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int taskNumberInList;
    public UnmarkCommand(int taskNumberInList) {
        this.taskNumberInList = taskNumberInList;
    }
    @Override
    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        taskParser.unmarkTask(ui, storage, taskNumberInList);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
