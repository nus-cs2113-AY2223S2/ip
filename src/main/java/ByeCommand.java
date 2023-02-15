// ByeCommand class that extends Command

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