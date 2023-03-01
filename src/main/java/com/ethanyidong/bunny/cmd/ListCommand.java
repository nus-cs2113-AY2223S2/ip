package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;

import java.util.ArrayList;

public class ListCommand extends ExecutableCommand {
    public void execute(BunnySession bunny) {
        if (bunny.getTasks().numTasks() == 0) {
            bunny.getUI().printMessage("Your TODO list is empty!");
        } else {
            ArrayList<String> messageLines = new ArrayList<>();
            for (int i = 0; i < bunny.getTasks().numTasks(); i++) {
                messageLines.add((i + 1) + ". " + bunny.getTasks().getTask(i));
            }
            bunny.getUI().printMessage(messageLines);
        }
    }
}
