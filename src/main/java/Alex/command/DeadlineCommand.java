package Alex.command;

import Alex.task.Deadline;
import Alex.task.Task;
import Alex.task.TaskList;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        Task deadline = new Deadline(description, COMMAND_WORD.substring(0, 1).toUpperCase(), by);
        taskList.setTask(deadline);
        return new CommandResult("Got it. I've added this task:" + "\n " + deadline + "\n" +
                "Now you have " + taskList.getNumberOfTasks() + " tasks in the list");
    }
}
