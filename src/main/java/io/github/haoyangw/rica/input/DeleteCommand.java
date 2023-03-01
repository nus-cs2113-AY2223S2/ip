package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.exception.RicaTaskException;
import io.github.haoyangw.rica.task.TaskManager;

public class DeleteCommand extends Command {

    public DeleteCommand(String command, TaskManager taskManager) {
        super(command, taskManager);
    }

    /**
     * Executes the 'delete' command, deleting a specified Task from Rica's memory
     *
     * @throws RicaTaskException If specified Task could not be removed by TaskManager
     */
    @Override
    public void run() throws RicaTaskException {
        try {
            super.getTaskManager().rmTask(super.getCommand());
        } catch (RicaTaskException exception) {
            super.getTextUi().printErrorMessage(exception);
        }
    }
}
