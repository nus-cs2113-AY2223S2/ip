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

    /**
     * This is the constructor for the DeleteCommand class.
     * @param taskNumber the number of the task to be deleted
     */
    public DeleteCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }

    /**
     * This method executes the command to delete a task from the task list.
     * @param tasks the task list
     * @param ui the user interface
     * @throws DukeException if the deadline is not in the correct format
     */
    public void execute(TaskList tasks, Ui ui) throws DukeException{
        //print the message
        ui.printDeletedTask(tasks.get(taskNumber), tasks.size()-1);
        //delete the task from the task list
        tasks.delete(taskNumber);
    }

    /**
     * This method checks if the command is an exit command.
     * @return false if the command is not an exit command
     */
    public boolean isExit(){
        return false;
    }
}
