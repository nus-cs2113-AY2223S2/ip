package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;

import java.util.ArrayList;

public class ListCommand extends ExecutableCommand {
    public void execute(BunnySession bunny) {
        if (bunny.numTasks() == 0) {
            bunny.printMessage("Your TODO list is empty!");
        } else {
            ArrayList<String> messageLines = new ArrayList<>();
            for (int i = 0; i < bunny.numTasks(); i++) {
                messageLines.add((i + 1) + ". " + bunny.getTask(i));
            }
            bunny.printMessage(messageLines);
        }
    }
}
