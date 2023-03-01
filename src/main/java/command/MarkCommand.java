package command;

import exceptions.TaskIndexNotFoundException;
import task.Task;
import task.TaskList;
import ui.Ui;
import utils.Storage;

import java.io.IOException;

public class MarkCommand extends Command{
    String taskToMarkIndexString;
    int index;
    boolean isMarkAsDone;

    public MarkCommand(String taskToMarkIndexString, boolean isMarkAsDone){
        this.taskToMarkIndexString = taskToMarkIndexString;
        this.isMarkAsDone = isMarkAsDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        //Input cannot format into an Index
        try{
            index = Integer.parseInt(taskToMarkIndexString);
        }catch (NumberFormatException nfe){
            ui.showTaskIndexFormatError();
            return;
        }

        //Input Index out of range
        index = index - 1;
        if(index < 0 || index >= tasks.size()){
            ui.showTaskIndexNotFoundError();
            return;
        }

        //set mark or unmark status, and get feedback
        Task taskToMark = tasks.get(index);
        taskToMark.setStatus(isMarkAsDone);

        storage.rewrite(tasks, ui);

        ui.showMarkUnmarkTask(taskToMark, isMarkAsDone);

    }
    public boolean isExit(){
        return false;
    }
}
