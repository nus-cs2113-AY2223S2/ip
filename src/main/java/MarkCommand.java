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
    //constructor
    public MarkCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }
    //execute the command
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        //mark the task from the task list
        tasks.markTask(taskNumber);
        //print the message
        ui.printDoneTask(tasks.get(taskNumber));
    }
    //check if the command is an exit command
    public boolean isExit(){
        return false;
    }
}