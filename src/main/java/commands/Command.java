package commands;

import storage.Storage;
import task.TaskParser;
import ui.TextUi;

public abstract class Command {

    public abstract void execute(TaskParser taskParser, TextUi ui, Storage storage);

    public abstract boolean isExit();
}
