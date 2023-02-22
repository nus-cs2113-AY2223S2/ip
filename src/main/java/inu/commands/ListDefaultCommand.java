package inu.commands;

import inu.commons.Messages;
import inu.task.TaskList;

public class ListDefaultCommand extends Command {

    public ListDefaultCommand() {}

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(Messages.MESSAGE_LIST_HEADER + "\n" + taskList.printList());
    }
}
