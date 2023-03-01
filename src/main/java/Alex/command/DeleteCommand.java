package Alex.command;

import Alex.task.Task;
import Alex.task.TaskList;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private int position;

    /**
     * Constructor when a DeleteCommand is initialized

     * @param position index position of command to be deleted in taskList
     */
    public DeleteCommand(int position) {
        this.position = position;
    }

    /**
     * Executes the command and returns the result.
     *
     * @param taskList the taskList that contains all tasks of the user
     * @return CommandResult that will show which task user removed and number of tasks left
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        Task t = taskList.getAllTasks().get(position - 1);
        taskList.deleteTask(position - 1);
        return new CommandResult("Noted. I've removed this task:" + System.lineSeparator()
                + t + System.lineSeparator() + "Now you have " + taskList.getNumberOfTasks() + " tasks in the list."
        );
    }

}


