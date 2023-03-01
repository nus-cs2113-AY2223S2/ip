package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.task.TaskManager;
import io.github.haoyangw.rica.ui.TextUi;

public class ByeCommand extends Command {

    public ByeCommand(String command, TaskManager taskManager) {
        super(command, taskManager);
    }

    /**
     * Executes the 'bye' command issued by the user, which involves printing Rica's
     *   signature goodbye message to tell the user how much she'll miss him/her
     */
    @Override
    public void run() {
        super.getTextUi().printGoodbyeMessage();
    }
}
