package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.arg.CommandValidator;
import com.ethanyidong.bunny.arg.FlagArgumentCommandValidator;
import com.ethanyidong.bunny.arg.NotEmptyArgumentValidator;
import com.ethanyidong.bunny.arg.PositionalArgumentCommandValidator;
import com.ethanyidong.bunny.task.Deadline;
import com.ethanyidong.bunny.task.Task;

/**
 * An implementation of <code>ExecutableCommand</code> to represent the 'deadline' command
 */
public class AddDeadlineCommand extends AddTaskCommand {
    private String by;

    /**
     * @return Validators checking that the name (positional argument) and /by (flag argument) are both not empty
     */
    @Override
    protected CommandValidator[] getValidators() {
        CommandValidator deadlineNameValidator =
                new PositionalArgumentCommandValidator(new NotEmptyArgumentValidator());
        CommandValidator byValidator =
                new FlagArgumentCommandValidator("by", new NotEmptyArgumentValidator());
        return new CommandValidator[]{deadlineNameValidator, byValidator};
    }

    @Override
    protected void parseArguments(BunnySession bunny, TokenizedCommand command) {
        this.name = command.getPositionalArgument();
        this.by = command.getFlagArgument("by");
    }

    protected Task generateTask() {
        return new Deadline(this.name, this.by);
    }
}
