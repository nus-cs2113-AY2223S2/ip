// Unmark class that extends Command

public class UnmarkCommand extends Command {
    //the task number to be unmarked
    private int taskNumber;
    //constructor
    public UnmarkCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }
    //execute the command
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        //unmark the task from the task list
        tasks.unmarkTask(taskNumber);
        //print the message
        ui.printUndoneTask(tasks.get(taskNumber));
    }
    //check if the command is an exit command
    public boolean isExit(){
        return false;
    }
}