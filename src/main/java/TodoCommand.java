/**
 * This class represents the command to add a todo task to the task list.
 * It is a subclass of the Command class.
 * It contains the execute method to add the todo task to the task list.
 * It also contains the isExit method to return false.
 * @param taskName the name of the task
 * @param execute method to execute the command
 * @param isExit method to return false if the command is not an exit command
 * @throws DukeException if the deadline is not in the correct format
 */

public class TodoCommand extends Command {
    protected String taskName;

    /**
     * This constructor takes in the task name.
     * @param taskName the name of the task
     */
    public TodoCommand(String taskName){
        this.taskName = taskName;
    }

    /**
     * This method executes the todo command.
     * @param tasks the task list
     * @param ui the user interface
     * @throws DukeException if the deadline is not in the correct format
     */
    public void execute(TaskList tasks, Ui ui) throws DukeException{
        Todo todo = new Todo(taskName);
        tasks.add(todo);
        ui.printAddedTask(todo, tasks.size());
    }

    /**
     * This method checks if the command is an exit command.
     * @return false if the command is not an exit command
     */
    public boolean isExit(){
        return false;
    }
}
