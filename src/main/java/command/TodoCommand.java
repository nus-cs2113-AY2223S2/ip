package command;

import task.TaskList;
import task.Todo;
import ui.Ui;
import utils.Storage;

import java.io.IOException;

public class TodoCommand extends Command{
    String todoString;
    public TodoCommand(String todoString){
        this.todoString = todoString;
    }

    void checkTodoString(String todoString){
        if(todoString == null){
            throw new NullPointerException();
        }
    }
    public void execute(TaskList tasks, Ui ui, Storage storage){
        try {
            checkTodoString(todoString);
        }catch(NullPointerException e){
            ui.showNullPointerException();
            return;
        }

        Todo newTodoObject = new Todo(todoString);
        tasks.add(newTodoObject);
        storage.addNewObjectToFile(newTodoObject, ui);

        ui.showTodoTask(newTodoObject, tasks);
    }

    public boolean isExit(){
        return false;
    }

}
