package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.arg.CommandValidator;
import com.ethanyidong.bunny.arg.NotEmptyArgumentValidator;
import com.ethanyidong.bunny.arg.PositionalArgumentCommandValidator;

import java.util.ArrayList;

/**
 * An implementation of <code>ExecutableCommand</code> to represent the 'find' command
 */
public class FindCommand extends ExecutableCommand {
    private String keyword;

    /**
     * @return Validators checking that the keywords (positional argument) is not empty
     */
    @Override
    protected CommandValidator[] getValidators() {
        CommandValidator todoNameValidator =
                new PositionalArgumentCommandValidator(new NotEmptyArgumentValidator());
        return new CommandValidator[]{todoNameValidator};
    }

    @Override
    protected void parseArguments(BunnySession bunny, TokenizedCommand command) {
        this.keyword = command.getPositionalArgument();
    }

    /**
     * Lists the tasks in the current Bunny session containing the specified keyword
     *
     * @param bunny the current Bunny session
     */
    public void execute(BunnySession bunny) {
        if (bunny.getTasks().numTasks() == 0) {
            bunny.getUI().printMessage("Your TODO list is empty!");
            return;
        }
        ArrayList<String> messageLines = new ArrayList<>();
        int taskLineNum = 1;
        for (int i = 0; i < bunny.getTasks().numTasks(); i++) {
            if (!bunny.getTasks().getTask(i).getName().contains(keyword)) {
                continue;
            }
            messageLines.add(String.format(bunny.getUI().LIST_ELEMENT_MESSAGE_FORMAT,
                    taskLineNum, bunny.getTasks().getTask(i)));
            taskLineNum++;
        }
        bunny.getUI().printMessage(messageLines);
    }
}

