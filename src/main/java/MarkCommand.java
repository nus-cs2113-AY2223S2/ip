/**
 * This class represents the command to mark a task as done.
 * It is a subclass of the Command class.
 * It contains the execute method to mark the task as done.
 * It also contains the isExit method to return false.
 * @param taskNumber the number of the task to be marked
 * @param execute method to execute the command
 * @param isExit method to return false if the command is not an exit command
 * @throws DukeException if the deadline is not in the correct format
 */

public class MarkCommand extends Command {

    //the task number to be marked
    private int taskNumber;

    /**
     * This is the constructor for the MarkCommand class.
     * @param taskNumber the number of the task to be marked
     */
    public MarkCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }

    /**
     * This method executes the command to mark a task as done.
     * @param tasks the task list
     * @param ui the user interface
     * @throws DukeException if the deadline is not in the correct format
     */
    public void execute(TaskList tasks, Ui ui) throws DukeException{
        //mark the task from the task list
        tasks.markTask(taskNumber);
        //print the message
        ui.printDoneTask(tasks.get(taskNumber));
    }

    /**
     * This method checks if the command is an exit command.
     * @return false if the command is not an exit command
     */
    public boolean isExit(){
        return false;
    }
}