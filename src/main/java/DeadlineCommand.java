// Deadline class that inherits from the command class and overrides the execute method

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
