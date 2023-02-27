package commands;

import common.Messages;
import storage.Storage;
import task.TaskParser;
import ui.TextUi;

public class IncorrectCommand extends Command {
    public IncorrectCommand() {
    }
    @Override
    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        //ui.printMessage(Messages.ERROR_INVALID_COMMAND);
        ui.printMessage(Messages.MESSAGE_VALID_COMMAND_LIST);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
