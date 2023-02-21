/**
 * This class represents the command to add a deadline task to the task list.
 * It is a subclass of the Command class.
 * It contains the execute method to add the deadline task to the task list.
 * It also contains the isExit method to return false.
 * @param taskName the name of the task
 * @param deadline the deadline of the task
 * @param execute method to execute the command
 * @param isExit method to return false if the command is not an exit command
 * @throws DukeException if the deadline is not in the correct format
 */

public class DeadlineCommand extends Command {
    
    protected String taskName;
    protected String deadline;

    /**
     * This constructor takes in the task name and deadline.
     * @param taskName the name of the task
     * @param deadline the deadline of the task
     */
    public DeadlineCommand(String taskName, String deadline){
        this.taskName = taskName;
        this.deadline = deadline;
    }

    /**
     * This method executes the deadline command.
     * @param tasks the task list
     * @param ui the user interface
     * @throws DukeException if the deadline is not in the correct format
     */
    public void execute(TaskList tasks, Ui ui) throws DukeException{
        Deadline deadline = new Deadline(taskName, this.deadline);
        tasks.add(deadline);
        ui.printAddedTask(deadline, tasks.size());
    }

    /**
     * This method checks if the command is an exit command.
     * @return false if the command is not an exit command
     */
    public boolean isExit(){
        return false;
    }
}
