package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.exception.RicaTaskException;
import io.github.haoyangw.rica.task.TaskManager;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String command, TaskManager taskManager) {
        super(command, taskManager);
    }

    /**
     * Executes the 'deadline' command issued by the user, creating a new Deadline
     *   Task for Rica to keep track of
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
