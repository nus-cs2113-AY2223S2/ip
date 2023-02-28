package Alex.command;

import Alex.task.TaskList;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int position;

    public MarkCommand(int position) {
        this.position = position;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.getAllTasks().get(position - 1).markAsDone();
        return new CommandResult("Good job! I have marked this task as completed:" + System.lineSeparator() +
                taskList.getAllTasks().get(position - 1));
    }

}
