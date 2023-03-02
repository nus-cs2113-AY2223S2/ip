package command;

import exceptions.DeadlineParamsFormatException;
import task.Deadline;
import task.TaskList;
import ui.Ui;
import utils.Storage;

import java.io.FileWriter;
import java.io.IOException;


public class DeadlineCommand extends Command{
    protected String todoString;
    protected String deadlineString;



    public DeadlineCommand(String todoString, String deadlineString) {
        this.todoString = todoString;
        this.deadlineString = deadlineString;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage){
        Deadline newDeadlineObject = new Deadline(todoString, deadlineString);
        tasks.add(newDeadlineObject);
        storage.addNewObjectToFile(newDeadlineObject, ui);

        ui.showDeadlineTask(newDeadlineObject, tasks);
    }

    public boolean isExit(){
        return false;
    }
}
