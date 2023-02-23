package command;

import exception.DukeException;
import task.Task;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;

public class DeleteCommand extends Command {

    public DeleteCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public String doCommand(ArrayList<Task> tasks) throws DukeException {
        try {
            StringBuilder result = new StringBuilder();
            int taskNum = Integer.parseInt(getCommands().get(1));
            String taskSummary = tasks.get(taskNum - 1).getSummary();
            tasks.remove(taskNum - 1);
            result.append("____________________________________________________________\n");
            result.append("Noted. I've removed this task:\n");
            result.append(taskSummary).append("\n");
            result.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
            result.append("____________________________________________________________");
            return String.valueOf(result);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException("Invalid task number!");
        }
    }
}
