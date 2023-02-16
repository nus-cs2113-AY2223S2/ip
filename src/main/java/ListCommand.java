//List Command class that extends Command

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