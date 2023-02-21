/**
 * This class represents the command to delete a task from the task list.
 * It is a subclass of the Command class.
 * It contains the execute method to delete the task from the task list.
 * It also contains the isExit method to return false.
 * @param taskNumber the number of the task to be deleted
 * @param execute method to execute the command
 * @param isExit method to return false if the command is not an exit command
 * @throws DukeException if the deadline is not in the correct format
 */

public class DeleteCommand extends Command {
    //the task number to be deleted
    private int taskNumber;
    //constructor
    public DeleteCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }
    //execute the command
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        //print the message
        ui.printDeletedTask(tasks.get(taskNumber), tasks.size()-1);
        //delete the task from the task list
        tasks.delete(taskNumber);
    }
    //check if the command is an exit command
    public boolean isExit(){
        return false;
    }
}
