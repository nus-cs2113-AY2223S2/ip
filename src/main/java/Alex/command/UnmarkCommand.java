package Alex.command;

import Alex.task.TaskList;

public class UnmarkCommand extends  Command{
    public static final String COMMAND_WORD = "unmark";

    private int position;

    public UnmarkCommand(int position) {
        this.position = position;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.getAllTasks().get(position - 1).unmark();
        return new CommandResult("Good job! I have marked this task as not yet completed:" + System.lineSeparator() +
                taskList.getAllTasks().get(position - 1));
    }



}
