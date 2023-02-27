package commands;

import storage.Storage;
import task.TaskParser;
import ui.TextUi;

public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "bye";
    private boolean isExit;

    public ExitCommand() {
        this.isExit = false;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    @Override
    public boolean isExit() {
        return isExit;
    }

    @Override
    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        setExit(true);
    }
}
