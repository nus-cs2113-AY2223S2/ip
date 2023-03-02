package command;

import task.TaskList;
import ui.Ui;
import utils.Storage;

/**
 *  Command when parser meets an unknown task.
 *  Will do nothing, but call the ui to indicate user has input an unknown command.
 */
public class UnknownCommand extends Command{
    protected String userCommand;
    public UnknownCommand(String userCommand){
        this.userCommand = userCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showUnknownCommand(userCommand);
    }
    public boolean isExit(){
        return false;
    }
}
