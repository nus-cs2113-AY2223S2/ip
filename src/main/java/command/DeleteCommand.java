package command;

import task.TaskList;
import ui.Ui;
import utils.Storage;


/**
 *  Command for deleting a task in the tasklist
 *  Format: delete [index]
 */
public class DeleteCommand extends Command{
    protected int index;
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

    private void checkIndex(TaskList tasks){
        if(index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Execute the delete command. Delete the object according to the index,
     * and update the local file.
     * @param tasks The task list.
     * @param ui The ui module to show messages.
     * @param storage The reading and writing tool.
     */
    @Override
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
