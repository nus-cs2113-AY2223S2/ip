package Alex.command;

import Alex.task.TaskList;

public class UnmarkCommand extends  Command{
    public static final String COMMAND_WORD = "unmark";

    private int position;

    /**
     * Constructor to be executed when UnmarkCommand Object is initialized
     *
     * @param position index position of the task in taskList to be unmarked
     */
    public UnmarkCommand(int position) {
        this.position = position;
    }

    /**
     * Executes the command and returns the result.
     *
     * @param taskList the taskList that contains all tasks of the user
     * @return CommandResult that will show which task user unmarked and total number of tasks
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.getAllTasks().get(position - 1).setUndone();
        return new CommandResult("Good job! I have marked this task as not yet completed:" + System.lineSeparator() +
                taskList.getAllTasks().get(position - 1));
    }



}
