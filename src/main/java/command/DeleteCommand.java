package command;

import exception.DukeException;
import taskList.TaskList;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private final int TASK_NUM = 1;

    public DeleteCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public String doCommand(TaskList taskList) throws DukeException {
        try {
            StringBuilder result = new StringBuilder();
            int taskNum = Integer.parseInt(getCommands().get(TASK_NUM));
            String taskSummary = taskList.get(taskNum - 1).getSummary();
            taskList.remove(taskNum - 1);
            result.append("Noted. I've removed this task:\n");
            result.append(taskSummary).append("\n");
            result.append("Now you have ").append(taskList.size()).append(" tasks in the list.\n");
            return String.valueOf(result);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException("Invalid task number!");
        }
    }
}
