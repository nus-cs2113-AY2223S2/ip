package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.arg.CommandValidator;
import com.ethanyidong.bunny.arg.PositionalArgumentCommandValidator;
import com.ethanyidong.bunny.arg.TaskIndexArgumentValidator;

public class MarkCommand extends ExecutableCommand {
    private final boolean isDone;
    private int taskIndex;

    public MarkCommand(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public CommandValidator[] validators() {
        CommandValidator markIndexValidator =
                new PositionalArgumentCommandValidator(new TaskIndexArgumentValidator());
        return new CommandValidator[]{markIndexValidator};
    }

    @Override
    protected void parseArguments(BunnySession bunny, TokenizedCommand command) {
        this.taskIndex = Integer.parseInt(command.getPositionalArgument()) - 1;
    }

    public String markedMessage() {
        if (this.isDone) {
            return "Nice! I've marked this task as done";
        } else {
            return "Nice! I've marked this task as not done yet";
        }
    }

    public void execute(BunnySession bunny) {
        bunny.getTasks().getTask(this.taskIndex).setIsDone(this.isDone);
        bunny.getUI().printMessage(this.markedMessage() + ":\n\t" + bunny.getTasks().getTask(this.taskIndex));
    }
}
