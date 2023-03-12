package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.arg.CommandValidator;
import com.ethanyidong.bunny.arg.NotEmptyArgumentValidator;
import com.ethanyidong.bunny.arg.PositionalArgumentCommandValidator;
import com.ethanyidong.bunny.task.Task;
import com.ethanyidong.bunny.task.Todo;

/**
 * An implementation of <code>ExecutableCommand</code> to represent the 'todo' command
 */
public class AddTodoCommand extends AddTaskCommand {
    /**
     * @return Validators checking that the name (positional argument) and /by (flag argument) are both not empty
     */
    @Override
    protected CommandValidator[] getValidators() {
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
