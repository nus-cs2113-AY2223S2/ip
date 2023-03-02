package Alex.command;

import Alex.task.TaskList;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int position;

    /**
     * Constructor to be executed when MarkCommand Object is initialized
     *
     * @param position index position of the task in taskList to be marked
     */
    public MarkCommand(int position) {
        this.position = position;
    }
    /**
     * Executes the command and returns the result.
     *
     * @param taskList the taskList that contains all tasks of the user
     * @return CommandResult that will show which task will be marked as completed
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.getAllTasks().get(position - 1).setAsDone();
        return new CommandResult("Good job! I have marked this task as completed:" + System.lineSeparator() +
                taskList.getAllTasks().get(position - 1));
    }

}
