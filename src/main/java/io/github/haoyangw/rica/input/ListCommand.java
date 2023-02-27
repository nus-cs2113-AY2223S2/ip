package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.task.TaskManager;
import io.github.haoyangw.rica.ui.TextUi;

public class ListCommand extends Command {

    public ListCommand(String command, TaskManager taskManager) {
        super(command, taskManager);
    }

    @Override
    public void run() {
        super.getTaskManager().printTasks();
    }
}
