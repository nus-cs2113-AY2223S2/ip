package Alex.command;

import Alex.task.Deadline;
import Alex.task.Task;
import Alex.task.TaskList;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private String description;
    private String by;

    /**
     * Constructor when a DeadlineCommand is initialized
     *
     * @param description description of activity
     * @param by to be completed time
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }
    /**
     * Executes the command and returns the result.
     *
     * @param taskList that taskList contains all tasks of the user
     * @return CommandResult that will show which task he added and the number of total task
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        Task deadline = new Deadline(description, COMMAND_WORD.substring(0, 1).toUpperCase(), by);
        taskList.setTask(deadline);
        return new CommandResult("Got it. I've added this task:" + "\n " + deadline + "\n" +
                "Now you have " + taskList.getNumberOfTasks() + " tasks in the list");
    }
}
