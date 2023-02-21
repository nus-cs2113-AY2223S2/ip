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
    //execute the command
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        //print the message
        ui.printGoodbyeMessage();
    }
    //check if the command is an exit command
    public boolean isExit(){
        return true;
    }
}