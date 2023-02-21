/**
 * This class represents the command to add a deadline task to the task list.
 * It is a subclass of the Command class.
 * It contains the execute method to add the deadline task to the task list.
 * It also contains the isExit method to return false.
 * @param taskName the name of the task
 * @param deadline the deadline of the task
 * @param execute method to execute the command
 * @param isExit method to return false if the command is not an exit command
 * @throws DukeException if the deadline is not in the correct format
 */

public class Command {

    //constructor
    public Command(){
        
    }

    //method that executes the command
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
    
    }

    //method that returns the if the command is a bye command
    public boolean isExit(){
        //return false
        return false;
    }
    
}
