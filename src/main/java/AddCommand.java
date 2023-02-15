//AddCommand that extends Command
public class AddCommand extends Command {
    //the task to be added
    private Task task;
    //constructor
    public AddCommand(Task task){
        this.task = task;
    }
    //execute the command
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        //add the task to the task list
        tasks.add(task);
        //print the message
        ui.printAddedTask(task, tasks.size());
    }
    //check if the command is an exit command
    public boolean isExit(){
        return false;
    }
}
