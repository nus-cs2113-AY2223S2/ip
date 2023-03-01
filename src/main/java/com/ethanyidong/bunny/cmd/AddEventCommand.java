package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.arg.CommandValidator;
import com.ethanyidong.bunny.arg.FlagArgumentCommandValidator;
import com.ethanyidong.bunny.arg.NotEmptyArgumentValidator;
import com.ethanyidong.bunny.arg.PositionalArgumentCommandValidator;
import com.ethanyidong.bunny.task.Event;
import com.ethanyidong.bunny.task.Task;

/**
 * An implementation of <code>ExecutableCommand</code> to represent the 'event' command
 */
public class AddEventCommand extends AddTaskCommand {
    private String from;
    private String to;

    /**
     * @return Validators checking that the name (positional argument) and /from, /to (flag arguments) are not empty
     */
    @Override
    protected CommandValidator[] getValidators() {
        CommandValidator eventNameValidator =
                new PositionalArgumentCommandValidator(new NotEmptyArgumentValidator());
        CommandValidator fromValidator =
                new FlagArgumentCommandValidator("from", new NotEmptyArgumentValidator());
        CommandValidator toValidator =
                new FlagArgumentCommandValidator("to", new NotEmptyArgumentValidator());
        return new CommandValidator[]{eventNameValidator, fromValidator, toValidator};
    }

    @Override
    protected void parseArguments(BunnySession bunny, TokenizedCommand command) {
        this.name = command.getPositionalArgument();
        this.from = command.getFlagArgument("from");
        this.to = command.getFlagArgument("to");
    }

    protected Task generateTask() {
        return new Event(this.name, this.from, this.to);
    }
}
