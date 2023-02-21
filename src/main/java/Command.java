/**
 * This class represents all commands.
 * It is the parent class of all the other command classes.
 * It is created by the Parser class if the command is invalid.
 * It contains the execute method to throw an exception since it is an invalid command.
 * It also contains the isExit method to return false.
 * @param execute method to execute the command
 * @param isExit method to return false if the command is not an exit command
 * @throws DukeException if it is an invalid command
 */

public class Command {

    //constructor
    public Command(){
        
    }

    /**
     * This method executes the command.
     * @param tasks the task list
     * @param ui the user interface
     * @throws DukeException if it is an invalid command
     */
    public void execute(TaskList tasks, Ui ui) throws DukeException{
        //throw invalid command exception
        throw new DukeException("Invalid command");

    }

    /**
     * This method checks if the command is an exit command.
     * @return false if the command is not an exit command
     */
    public boolean isExit(){
        //return false
        return false;
    }
    
}
