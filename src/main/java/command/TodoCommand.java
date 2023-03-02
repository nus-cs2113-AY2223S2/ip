package command;

import task.TaskList;
import task.Todo;
import ui.Ui;
import utils.Storage;


/**
 *  Command for adding a todo task.
 *  Format: todo [description]
 */
public class TodoCommand extends Command{
    protected String todoString;
    public TodoCommand(String todoString){
        this.todoString = todoString;
    }

    /* Check whether input is null */
    private void checkTodoString(String todoString){
        if(todoString == null){
            throw new NullPointerException();
        }
    }

    @Override
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

        ui.showTodoTaskDone(newTodoObject, tasks);
    }

    public boolean isExit(){
        return false;
    }

}
