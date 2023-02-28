package duke.commands;
import duke.tasks.Deadline;
import duke.outputs.Messages;
import duke.exception.InvalidDateTimeFormatException;

public class DeadlineCommand extends TaskCommand{
    private String taskDescription;
    private String by;

    public DeadlineCommand(String taskDescription, String by) {
        super("deadline");
        this.taskDescription = taskDescription;
        this.by = by;
    }
    public CommandResult execute() {
        try {
            Deadline task = new Deadline(taskDescription, by);
            tasklist.addTask(task);
            return new CommandResult(Messages.MESSAGE_TASK_ADDED,
                    String.format("\n\t\t%s \n", task),
                    String.format(Messages.MESSAGE_TASK_LIST_SIZE, tasklist.getNumberOfTasks()));
        } catch(InvalidDateTimeFormatException exception){
            return new CommandResult(exception.getDukeMessages());
        }
    }
}
