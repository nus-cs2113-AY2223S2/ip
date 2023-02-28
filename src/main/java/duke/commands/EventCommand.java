package duke.commands;
import duke.tasks.Event;
import duke.exception.InvalidDateTimeFormatException;
import duke.outputs.Messages;

public class EventCommand extends TaskCommand{
    private String taskDescription;
    private String startTime;
    private String endTime;

    public EventCommand(String taskDescription, String startTime, String endTime) {
        super("event");
        this.taskDescription = taskDescription;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public CommandResult execute() {
        try {
            Event task = new Event(taskDescription, startTime, endTime);
            tasklist.addTask(task);
            return new CommandResult(Messages.MESSAGE_TASK_ADDED,
                    String.format("\n\t\t%s \n", task),
                    String.format(Messages.MESSAGE_TASK_LIST_SIZE, tasklist.getNumberOfTasks()));
        } catch (InvalidDateTimeFormatException exception){
            return new CommandResult(exception.getDukeMessages());
        }
    }
}
