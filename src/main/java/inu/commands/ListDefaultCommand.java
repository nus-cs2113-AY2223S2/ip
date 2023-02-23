package inu.commands;

import inu.commons.Messages;
import inu.task.TaskList;

/**
 * List all the tasks in the task list to the user.
 */
public class ListDefaultCommand extends Command {

    /**
     * Constructor.
     */
    public ListDefaultCommand() {}

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(Messages.MESSAGE_LIST_HEADER + "\n" + taskList.printList(taskList));
    }
}
