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
    protected CommandValidator[] getValidators() {
        CommandValidator markIndexValidator =
                new PositionalArgumentCommandValidator(new TaskIndexArgumentValidator());
        return new CommandValidator[]{markIndexValidator};
    }

    @Override
    protected void parseArguments(BunnySession bunny, TokenizedCommand command) {
        this.taskIndex = Integer.parseInt(command.getPositionalArgument()) - 1;
    }

    private String markedMessage(BunnySession bunny) {
        if (this.isDone) {
            return bunny.getUI().MARKED_MESSAGE;
        } else {
            return bunny.getUI().UNMARKED_MESSAGE;
        }
    }

    /**
     * Marks the specified task as done or not done
     *
     * @param bunny the current Bunny session
     */
    public void execute(BunnySession bunny) {
        bunny.getTasks().getTask(this.taskIndex).setIsDone(this.isDone);
        bunny.getUI().printMessage(String.format(bunny.getUI().MARK_MESSAGE_FORMAT,
                this.markedMessage(bunny), bunny.getTasks().getTask(this.taskIndex)));
    }
}
