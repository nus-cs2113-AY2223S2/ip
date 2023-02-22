package inu.commands;

import inu.commons.Messages;
import inu.task.TaskList;

public class UnMarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    private final int targetIndex;

    public UnMarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public CommandResult execute(TaskList taskList) {
        taskList.unMarkTask(targetIndex);
        return new CommandResult(Messages.MESSAGE_UNMARK_TASK + "\n" + taskList.getTask(targetIndex).toString());
    }

}
