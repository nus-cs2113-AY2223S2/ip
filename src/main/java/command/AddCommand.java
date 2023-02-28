package command;

import task.Task;
import task.TaskList;
import ui.Ui;
import utils.Storage;

import java.io.IOException;

public class AddCommand extends Command{
    String newTaskString;

    public AddCommand(String newTaskString){
        this.newTaskString = newTaskString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Task newTaskObject = new Task(newTaskString);
        tasks.add(newTaskObject);

        storage.addNewObjectToFile(newTaskObject, ui);

        ui.showAddTask(newTaskObject,tasks);
    }


    public boolean isExit(){
        return false;
    }


}
