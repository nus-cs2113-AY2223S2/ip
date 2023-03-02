package command;

import exception.DukeException;
import taskList.TaskList;

import java.util.ArrayList;

public class MarkCommand extends Command {
    private final int TASK_NUM = 1;

    public MarkCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public String doCommand(TaskList taskList) throws DukeException {
        try {
            StringBuilder result = new StringBuilder();
            int taskNum = Integer.parseInt(getCommands().get(TASK_NUM));
            taskList.get(taskNum - 1).markDone();
            result.append("Nice! I've marked this task as done:\n").append(taskList.get(taskNum - 1).getSummary()).append("\n");
            return String.valueOf(result);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException("Invalid task number!");
        }
    }
}