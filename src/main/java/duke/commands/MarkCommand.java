package duke.commands;
import duke.tasks.Task;
import duke.outputs.Messages;

public class MarkCommand extends TaskCommand{
    private int index;

    public MarkCommand(int index){
        super("mark");
        this.index = index;
    }
    public CommandResult execute() {
        try {
            Task task = tasklist.getTask(index);
            task.markAsDone();
            return new CommandResult(Messages.MESSAGE_MARK_TASK,
                    String.format("\n\t\t%s \n", task));
        } catch (IndexOutOfBoundsException exception) {
            return new CommandResult(Messages.ERROR_MESSAGE_TASK_NOT_FOUND);
        }
    }
}
