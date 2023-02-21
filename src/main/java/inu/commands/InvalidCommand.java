package inu.commands;

import inu.commons.Messages;
import inu.commons.Ui;
import inu.task.TaskList;

public class InvalidCommand extends Command {

    private final String messagePrompt;

    public InvalidCommand(String messagePrompt) {
        this.messagePrompt = messagePrompt;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(messagePrompt);
    }

}
