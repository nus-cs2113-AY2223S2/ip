package command;

import task.Task;
import task.TaskList;
import ui.Ui;
import utils.Storage;

/**
 *  Command for adding a normal task.
 *  Format: add [description]
 */
public class AddCommand extends Command{
    protected String newTaskString;

    public AddCommand(String newTaskString){
        this.newTaskString = newTaskString;
    }

    /**
     * Execute the add command. Add a new normal object to the task list,
     * and write it to the disk.
     * @param tasks The task list.
     * @param ui The ui module to show messages.
     * @param storage The reading and writing tool.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Task newTaskObject = new Task(newTaskString);
        tasks.add(newTaskObject);

        storage.addNewObjectToFile(newTaskObject, ui);

        ui.showAddTaskDone(newTaskObject,tasks);
    }


    public boolean isExit(){
        return false;
    }


}
