package inu.commands;

import inu.commons.Messages;
import inu.task.TaskList;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public ExitCommand() {

    }

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(Messages.MESSAGE_EXIT + '\n'
                + " Saved " + taskList.getTaskListSize() + " tasks ^.^!");
    }

}
