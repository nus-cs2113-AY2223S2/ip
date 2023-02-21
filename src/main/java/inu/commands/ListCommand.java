package inu.commands;

import inu.commons.Messages;
import inu.exceptionhandling.EmptyTaskListException;
import inu.exceptionhandling.ExceptionManager;
import inu.task.TaskList;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand() {}

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(Messages.MESSAGE_LIST_HEADER + "\n" + taskList.printList());
    }
}
