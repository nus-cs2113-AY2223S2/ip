//Mark Command class that extends Command

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