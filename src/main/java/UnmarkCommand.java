/**
 * This class represents the command to unmark a task from the task list.
 * It is a subclass of the Command class.
 * It contains the execute method to unmark the task from the task list.
 * It also contains the isExit method to return false.
 * @param taskNumber the number of the task to be unmarked
 * @param execute method to execute the command
 * @param isExit method to return false if the command is not an exit command
 * @throws DukeException if the deadline is not in the correct format
 */

public class UnmarkCommand extends Command {
    
    //the task number to be unmarked
    private int taskNumber;

    /**
     * This is the constructor for the UnmarkCommand class.
     * @param taskNumber the number of the task to be unmarked
     */
    public UnmarkCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }

    /**
     * This method executes the command to unmark a task from the task list.
     * @param tasks the task list
     * @param ui the user interface
     * @throws DukeException if the deadline is not in the correct format
     */
    public void execute(TaskList tasks, Ui ui) throws DukeException{
        //unmark the task from the task list
        tasks.unmarkTask(taskNumber);
        //print the message
        ui.printUndoneTask(tasks.get(taskNumber));
    }

    /**
     * This method checks if the command is an exit command.
     * @return false if the command is not an exit command
     */
    public boolean isExit(){
        return false;
    }
}