package duke.commands;
import duke.outputs.Messages;
import duke.tasks.Task;

public class UnmarkCommand extends TaskCommand{
    private int index;

    public UnmarkCommand(int index){
        super("unmark");
        this.index = index;
    }

    public CommandResult execute(){
        try{
            Task task =  tasklist.getTask(index);
            task.markAsUndone();
            return new CommandResult(Messages.MESSAGE_UNMARK_TASK,
                    String.format("\n\t\t%s \n", task));
        } catch (IndexOutOfBoundsException exception) {
            return new CommandResult(Messages.ERROR_MESSAGE_TASK_NOT_FOUND);
        }
    }

}
