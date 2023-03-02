package command;

import task.TaskList;
import ui.Ui;
import utils.Storage;

public class UnknownCommand extends Command{
    protected String userCommand;
    public UnknownCommand(String userCommand){
        this.userCommand = userCommand;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showUnknownCommand(userCommand);
    }
    public boolean isExit(){
        return false;
    }
}
