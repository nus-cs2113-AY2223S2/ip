package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.exception.RicaCommandException;
import io.github.haoyangw.rica.task.TaskManager;

public class MarkCommand extends Command {
    private static final String NOT_A_NUMBER_ERROR = " That's not a Task number is it? Or are you from another dimension hmm";

    public MarkCommand(String command, TaskManager taskManager) {
        super(command, taskManager);
    }

    /**
     * Executes the 'mark' command, which marks a specified Task as done by the user
     */
    @Override
    public void run() {
        String[] params = super.getCommand().split(Command.CMD_SEPARATOR);
        int SECOND_PARAM = 1;
        int indexOfTodo;
        try {
            indexOfTodo = Integer.parseInt(params[SECOND_PARAM]) - 1;
        } catch (NumberFormatException exception) {
            throw new RicaCommandException(MarkCommand.NOT_A_NUMBER_ERROR);
        }
        super.getTaskManager().markDone(indexOfTodo);
    }

}
