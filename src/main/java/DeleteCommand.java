//Delete command class that extends command

public class DeleteCommand extends Command {
    //the task number to be deleted
    private int taskNumber;
    //constructor
    public DeleteCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }
    //execute the command
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        //print the message
        ui.printDeletedTask(tasks.get(taskNumber), tasks.size()-1);
        //delete the task from the task list
        tasks.delete(taskNumber);
    }
    //check if the command is an exit command
    public boolean isExit(){
        return false;
    }
}
