package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.task.TaskManager;
import io.github.haoyangw.rica.ui.TextUi;

public class ByeCommand extends Command {

    public ByeCommand(String command, TaskManager taskManager) {
        super(command, taskManager);
    }

    @Override
    public void run() {
        super.getTextUi().printGoodbyeMessage();
    }
}
