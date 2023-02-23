package command;

import exception.DukeException;
import task.Task;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    public UnmarkCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public String doCommand(ArrayList<Task> tasks) throws DukeException {
        try {
            StringBuilder result = new StringBuilder();
            int taskNum = Integer.parseInt(getCommands().get(1));
            tasks.get(taskNum - 1).unmarkDone();
            result.append("____________________________________________________________" + "\nOK, I've marked this task as not done yet:\n").append(tasks.get(taskNum - 1).getSummary()).append("\n").append("____________________________________________________________");
            return String.valueOf(result);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException("Invalid task number!");
        }

    }
}
