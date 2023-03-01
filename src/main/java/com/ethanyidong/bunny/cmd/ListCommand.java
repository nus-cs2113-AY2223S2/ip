package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;

import java.util.ArrayList;

/**
 * An implementation of <code>ExecutableCommand</code> to represent the 'list' and command
 */
public class ListCommand extends ExecutableCommand {
    /**
     * Lists the tasks in the current Bunny session
     * @param bunny the current Bunny session
     */
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
