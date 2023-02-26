package duke.commands;
import duke.tasks.Task;
import duke.outputs.Messages;

public class DeleteCommand extends TaskCommand{
    private int index;

    public DeleteCommand(int index){
        super("delete");
        this.index = index;
    }
    public CommandResult execute() {
        try {
            Task task = tasklist.getTask(index);
            tasklist.deleteTask(index);
            return new CommandResult(Messages.MESSAGE_DELETE_TASK,
                    String.format("\n\t\t%s \n", task));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.ERROR_MESSAGE_TASK_NOT_FOUND);
        }
    }
}
