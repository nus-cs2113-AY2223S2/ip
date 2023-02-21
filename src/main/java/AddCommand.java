/**
 * Represents a command to add a task to the task list.
 * It is a subclass of the Command class.
 * @param task the task to be added
 * @param execute method to execute the command
 * @throws DukeException if the deadline is not in the correct format
 */

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
