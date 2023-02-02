package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.arg.CommandValidator;
import com.ethanyidong.bunny.arg.FlagArgumentCommandValidator;
import com.ethanyidong.bunny.arg.NotEmptyArgumentValidator;
import com.ethanyidong.bunny.arg.PositionalArgumentCommandValidator;
import com.ethanyidong.bunny.task.Deadline;
import com.ethanyidong.bunny.task.Task;

public class AddDeadlineCommand extends AddTaskCommand {
    private String name;
    private String by;

    @Override
    public CommandValidator[] validators() {
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
