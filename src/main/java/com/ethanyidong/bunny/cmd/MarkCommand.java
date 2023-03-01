package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.arg.CommandValidator;
import com.ethanyidong.bunny.arg.PositionalArgumentCommandValidator;
import com.ethanyidong.bunny.arg.TaskIndexArgumentValidator;

/**
 * An implementation of <code>ExecutableCommand</code> to represent the 'mark' and 'unmark' commands
 */
public class MarkCommand extends ExecutableCommand {
    private final boolean isDone;
    private int taskIndex;

    /**
     * @param isDone whether the command should mark the task as done or not done
     */
    public MarkCommand(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * @return Validators checking that the index (positional argument) is a valid task index
     */
    @Override
    protected CommandValidator[] validators() {
        CommandValidator markIndexValidator =
                new PositionalArgumentCommandValidator(new TaskIndexArgumentValidator());
        return new CommandValidator[]{markIndexValidator};
    }

    @Override
    protected void parseArguments(BunnySession bunny, TokenizedCommand command) {
        this.taskIndex = Integer.parseInt(command.getPositionalArgument()) - 1;
    }

    private String markedMessage() {
        if (this.isDone) {
            return "Nice! I've marked this task as done";
        } else {
            return "Nice! I've marked this task as not done yet";
        }
    }

    /**
     * Marks the specified task as done or not done
     * @param bunny the current Bunny session
     */
    public void execute(BunnySession bunny) {
        bunny.getTasks().getTask(this.taskIndex).setIsDone(this.isDone);
        bunny.getUI().printMessage(this.markedMessage() + ":\n\t" + bunny.getTasks().getTask(this.taskIndex));
    }
}
