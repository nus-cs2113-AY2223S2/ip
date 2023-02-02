package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.*;
import com.ethanyidong.bunny.arg.CommandValidator;
import com.ethanyidong.bunny.arg.FlagArgumentCommandValidator;
import com.ethanyidong.bunny.arg.NotEmptyArgumentValidator;
import com.ethanyidong.bunny.arg.PositionalArgumentCommandValidator;
import com.ethanyidong.bunny.task.Event;
import com.ethanyidong.bunny.task.Task;

public class AddEventCommand extends AddTaskCommand {
    private String name;
    private String from;
    private String to;

    @Override
    public CommandValidator[] validators() {
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
