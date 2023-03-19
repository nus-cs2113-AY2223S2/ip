package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.exception.RicaTaskException;
import io.github.haoyangw.rica.task.TaskManager;

public class EventCommand extends Command {

    public EventCommand(String command, TaskManager taskManager) {
        super(command, taskManager);
    }

    /**
     * Executes the 'event' command, creating a new Event for Rica to keep track of
     */
    @Override
    public void run() {
        try {
            super.getTaskManager().createTaskFrom(super.getCommand());
        } catch (RicaTaskException exception) {
            super.getTextUi().printErrorMessage(exception);
        }
    }
}
