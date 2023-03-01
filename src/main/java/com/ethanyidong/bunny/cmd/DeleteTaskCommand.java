package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.arg.CommandValidator;
import com.ethanyidong.bunny.arg.PositionalArgumentCommandValidator;
import com.ethanyidong.bunny.arg.TaskIndexArgumentValidator;
import com.ethanyidong.bunny.task.Task;

/**
 * An implementation of <code>ExecutableCommand</code> to represent the 'delete' command
 */
public class DeleteTaskCommand extends ExecutableCommand {
    private int taskIndex;

    /**
     * @return Validators checking that the index (positional argument) is a valid task index
     */
    @Override
    protected CommandValidator[] validators() {
        CommandValidator markIndexValidator =
                new PositionalArgumentCommandValidator(new TaskIndexArgumentValidator());
        return new CommandValidator[]{markIndexValidator};
    }

    protected void parseArguments(BunnySession bunny, TokenizedCommand command) {
        this.taskIndex = Integer.parseInt(command.getPositionalArgument()) - 1;
    }

    /**
     * Deletes a task from the current Bunny session
     * @param bunny the current Bunny session
     */
    public void execute(BunnySession bunny) {
        Task deletedTask = bunny.getTasks().getTask(this.taskIndex);
        bunny.getTasks().deleteTask(this.taskIndex);
        bunny.getUI().printMessage("Noted. I've removed this task:\n\t" + deletedTask + "\nNow you have " +
                bunny.getTasks().numTasks() + " tasks in the list.");
    }
}
