package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.arg.CommandValidator;
import com.ethanyidong.bunny.arg.NotEmptyArgumentValidator;
import com.ethanyidong.bunny.arg.PositionalArgumentCommandValidator;
import com.ethanyidong.bunny.task.Task;
import com.ethanyidong.bunny.task.Todo;

public class AddTodoCommand extends AddTaskCommand {
    private String name;

    @Override
    public CommandValidator[] validators() {
        CommandValidator todoNameValidator =
                new PositionalArgumentCommandValidator(new NotEmptyArgumentValidator());
        return new CommandValidator[]{todoNameValidator};
    }

    @Override
    protected void parseArguments(BunnySession bunny, TokenizedCommand command) {
        this.name = command.getPositionalArgument();
    }

    protected Task generateTask() {
        return new Todo(this.name);
    }
}
