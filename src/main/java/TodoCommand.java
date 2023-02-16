// Todocommand class that is used to create todo commands

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
