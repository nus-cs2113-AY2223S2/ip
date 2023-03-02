package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.task.TaskManager;

public class MarkCommand extends Command {

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
        int indexOfTodo = Integer.parseInt(params[SECOND_PARAM]) - 1;
        super.getTaskManager().markDone(indexOfTodo);
    }

}
