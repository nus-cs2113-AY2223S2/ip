package inu.commands;

import inu.task.TaskList;

/**
 * Represents an incorrect or unrecognised command.
 */
public class InvalidCommand extends Command {

    private final String messagePrompt;

    /**
     * Constructor.
     *
     * @param messagePrompt custom message prompt to be displayed to the user.
     */
    public InvalidCommand(String messagePrompt) {
        this.messagePrompt = messagePrompt;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(messagePrompt);
    }

}
