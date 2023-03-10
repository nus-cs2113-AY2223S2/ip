package command;

import task.TaskList;
import ui.Ui;
import utils.Storage;


public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showGoodBye();
    }

    public boolean isExit(){
        return true;
    }

}
