/**
 * This class represents the command to exit the program.
 * It is a subclass of the Command class.
 * It contains the execute method to print the goodbye message.
 * It also contains the isExit method to return true.
 * @param execute method to execute the command
 * @param isExit method to return true if the command is an exit command
 * @throws DukeException if the deadline is not in the correct format
 */

public class ByeCommand extends Command {

    //constructor
    public ByeCommand(){
        
    }

    /**
     * This method executes the command to exit the program.
     * @param tasks the task list
     * @param ui the user interface
     */
    public void execute(TaskList tasks, Ui ui) throws DukeException{
        //print the message
        ui.printGoodbyeMessage();
    }

    /**
     * This method checks if the command is an exit command.
     * @return true if the command is an exit command
     */
    public boolean isExit(){
        return true;
    }
}