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
    //constructor that takes in the task name and deadline
    public DeadlineCommand(String taskName, String deadline){
        this.taskName = taskName;
        this.deadline = deadline;
    }
    //method that executes the deadline command
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        Deadline deadline = new Deadline(taskName, this.deadline);
        tasks.add(deadline);
        ui.printAddedTask(deadline, tasks.size());
    }
    //method that checks if the command is an exit command
    public boolean isExit(){
        return false;
    }
}
