package inu.commands;

import inu.commons.Messages;
import inu.task.TaskList;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Contructor.
     */
    public ExitCommand() {}

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(Messages.MESSAGE_EXIT + '\n'
                + " Saved " + taskList.getTaskListSize() + " tasks ^.^!");
    }

}
