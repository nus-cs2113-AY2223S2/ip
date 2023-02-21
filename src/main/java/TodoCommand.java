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
    //constructor that takes in the task name
    public TodoCommand(String taskName){
        this.taskName = taskName;
    }
    //method that executes the todo command
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        Todo todo = new Todo(taskName);
        tasks.add(todo);
        ui.printAddedTask(todo, tasks.size());
    }
    //method that checks if the command is an exit command
    public boolean isExit(){
        return false;
    }
}
