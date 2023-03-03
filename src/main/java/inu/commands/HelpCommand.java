package inu.commands;

import inu.commons.Messages;
import inu.task.TaskList;

/**
 * Lists all commands supported and how they are used.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    /**
     * Constructor.
     */
    public HelpCommand() {}

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(Messages.MESSAGE_HELP);
    }
}
