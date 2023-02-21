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

    /**
     * This method executes the list command.
     * @param tasks the task list
     * @param ui the user interface
     */
    public void execute(TaskList tasks, Ui ui) throws DukeException{
        //print the message
        ui.printTaskList(tasks.getTasks());
    }

    /**
     * This method checks if the command is an exit command.
     * @return false if the command is not an exit command
     */
    public boolean isExit(){
        return false;
    }
}