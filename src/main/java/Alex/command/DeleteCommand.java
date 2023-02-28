package Alex.command;

import Alex.task.Task;
import Alex.task.TaskList;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private int position;

    public DeleteCommand(int position) {
        this.position = position;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        Task t = taskList.getAllTasks().get(position - 1);
        taskList.deleteTask(position - 1);
        return new CommandResult("Noted. I've removed this task:" + System.lineSeparator()
                + t + System.lineSeparator() + "Now you have " + taskList.getNumberOfTasks() + " tasks in the list."
        );
    }

}


