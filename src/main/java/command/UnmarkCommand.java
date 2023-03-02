package command;

import exception.DukeException;
import taskList.TaskList;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private final int TASK_NUM = 1;

    public UnmarkCommand(ArrayList<String> commands) {
        super(commands);
    }

    /**
     * Mark a task as not done.
     *
     * @return The message indicating the successful marking of a task as not done.
     */
    @Override
    public String doCommand(TaskList taskList) throws DukeException {
        try {
            StringBuilder result = new StringBuilder();
            int taskNum = Integer.parseInt(getCommands().get(TASK_NUM));
            taskList.get(taskNum - 1).unmarkDone();
            result.append("OK, I've marked this task as not done yet:\n").append(taskList.get(taskNum - 1).getSummary()).append("\n");
            return String.valueOf(result);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException("Invalid task number!");
        }

    }
}
