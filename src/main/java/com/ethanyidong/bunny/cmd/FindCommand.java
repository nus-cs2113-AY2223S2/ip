package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.arg.CommandValidator;
import com.ethanyidong.bunny.arg.NotEmptyArgumentValidator;
import com.ethanyidong.bunny.arg.PositionalArgumentCommandValidator;

import java.util.ArrayList;

public class FindCommand extends ExecutableCommand {
    private String keyword;
    @Override
    public CommandValidator[] validators() {
        CommandValidator todoNameValidator =
                new PositionalArgumentCommandValidator(new NotEmptyArgumentValidator());
        return new CommandValidator[]{todoNameValidator};
    }

    @Override
    protected void parseArguments(BunnySession bunny, TokenizedCommand command) {
        this.keyword = command.getPositionalArgument();
    }
    public void execute(BunnySession bunny) {
        if (bunny.getTasks().numTasks() == 0) {
            bunny.getUI().printMessage("Your TODO list is empty!");
        } else {
            ArrayList<String> messageLines = new ArrayList<>();
            int taskLineNum = 1;
            for (int i = 0; i < bunny.getTasks().numTasks(); i++) {
                if(!bunny.getTasks().getTask(i).getName().contains(keyword)) {
                    continue;
                }
                messageLines.add((taskLineNum) + ". " + bunny.getTasks().getTask(i));
                taskLineNum ++;
            }
            bunny.getUI().printMessage(messageLines);
        }
    }
}

