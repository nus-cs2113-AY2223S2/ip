package command;

import task.TaskList;
import ui.Ui;
import utils.Storage;

import java.io.IOException;

public class DeleteCommand extends Command{
    int index;
    public boolean isExit(){
        return false;
    }

    public DeleteCommand(String commandParams){
        try{
            index = Integer.parseInt(commandParams)-1;
        }catch (NumberFormatException e){
            index = -1;
        }

    }

    void checkIndex(TaskList tasks){
        if(index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
    }
    public void execute(TaskList tasks, Ui ui, Storage storage){
        //[have not done]: exceptions
        try{
            checkIndex(tasks);
        }catch (IndexOutOfBoundsException e){
            ui.showTaskIndexNotFoundError();
            return;
        }

        ui.showDeleteTask(tasks, index);

        tasks.remove(index);
        storage.rewrite(tasks, ui);

    }
}
