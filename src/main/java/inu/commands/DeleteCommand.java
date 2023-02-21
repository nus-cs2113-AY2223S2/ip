package inu.commands;

import inu.commons.Messages;
import inu.task.TaskList;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";


    private final int targetIndex;

    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        String deletedTask = taskList.getTask(targetIndex).toString();
        taskList.deleteTask(targetIndex);
        return new CommandResult(Messages.MESSAGE_DELETE_TASK + "\n" + deletedTask);
    }

}
