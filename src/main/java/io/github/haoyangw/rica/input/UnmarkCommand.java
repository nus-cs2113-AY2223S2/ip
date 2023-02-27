package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.task.TaskManager;

public class UnmarkCommand extends Command {

    public UnmarkCommand(String command, TaskManager taskManager) {
        super(command, taskManager);
    }

    @Override
    public void run() {
        String[] params = super.getCommand().split(Command.CMD_SEPARATOR);
        int indexOfTodo = Integer.parseInt(params[1]) - 1;
        super.getTaskManager().unmarkDone(indexOfTodo);
    }

}
