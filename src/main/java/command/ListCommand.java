package command;

import task.TaskList;
import ui.Ui;
import utils.Storage;

/**
 *  Command for listing tasks in tasklist
 *  Format: list
 */
public class ListCommand extends Command{
    public boolean isExit(){
        return false;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //[have not done]: exception
        ui.showTaskList(tasks);

    }
}
