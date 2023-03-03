package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.exception.RicaCommandException;
import io.github.haoyangw.rica.task.TaskManager;

public class UnmarkCommand extends Command {
    private static final String NOT_A_NUMBER_ERROR = " Hmm I don't think that's a Task number? Hope my CPU is working xP";

    public UnmarkCommand(String command, TaskManager taskManager) {
        super(command, taskManager);
    }

    /**
     * Executes the 'unmark' command, marking a specified Task as no longer already
     *   done by the user
     */
    @Override
    public void run() {
        String[] params = super.getCommand().split(Command.CMD_SEPARATOR);
        int SECOND_PARAM = 1;
        int indexOfTodo;
        try {
            indexOfTodo = Integer.parseInt(params[SECOND_PARAM]) - 1;
        } catch (NumberFormatException exception) {
            throw new RicaCommandException(UnmarkCommand.NOT_A_NUMBER_ERROR);
        }
        super.getTaskManager().unmarkDone(indexOfTodo);
    }

}
