package Alex.command;

import Alex.task.Event;
import Alex.task.Task;
import Alex.task.TaskList;

public class EventCommand extends Command{
    public static final String COMMAND_WORD = "event";

    private String description;
    private String from;
    private String to;


    /**
     * Constructor when a EventCommand is initialized
     *
     * @param description description of activity
     * @param from start time of event
     * @param to end time of event
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }



    /**
     * Executes the command and returns the result.
     *
     * @param taskList the taskList which contains all different tasks
     * @return CommandResult that will show which task user added and total number of tasks
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        Task event = new Event(description, COMMAND_WORD.substring(0,1).toUpperCase(), from, to);
        taskList.setTask(event);
        return new CommandResult("Got it. I've added this task:" + "\n " + event + "\n" +
                "Now you have " + taskList.getNumberOfTasks() + " tasks in the list");
    }
}
