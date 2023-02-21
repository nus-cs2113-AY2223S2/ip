/**
 * This class represents the command to list all the tasks in the task list.
 * It is a subclass of the Command class.
 * It contains the execute method to list all the tasks in the task list.
 * It also contains the isExit method to return false.
 * @param execute method to execute the command
 * @param isExit method to return false if the command is not an exit command
 * @throws DukeException if the deadline is not in the correct format
 */

public class ListCommand extends Command {
    //constructor
    public ListCommand(){
        
    }
    //execute the command
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        //print the message
        ui.printTaskList(tasks.getTasks());
    }
    //check if the command is an exit command
    public boolean isExit(){
        return false;
    }
}