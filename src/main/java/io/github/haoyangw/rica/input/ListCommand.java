package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.task.TaskManager;
import io.github.haoyangw.rica.ui.TextUi;

public class ListCommand extends Command {

    public ListCommand(String command, TaskManager taskManager) {
        super(command, taskManager);
    }

    /**
     * Executes the 'list' command, which lists out all Tasks that Rica is currently
     *   keeping track of on the user's screen
     */
    @Override
    public void run() {
        super.getTaskManager().printTasks();
    }
}
