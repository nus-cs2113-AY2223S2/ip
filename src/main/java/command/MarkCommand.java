package command;

import exception.DukeException;
import task.Task;

import java.util.ArrayList;

public class MarkCommand extends Command {
    public MarkCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public String doCommand(ArrayList<Task> tasks) throws DukeException {
        try {
            StringBuilder result = new StringBuilder();
            int taskNum = Integer.parseInt(getCommands().get(1));
            tasks.get(taskNum - 1).markDone();
            result.append("____________________________________________________________" + "\nNice! I've marked this task as done:\n").append(tasks.get(taskNum - 1).getSummary()).append("\n").append("____________________________________________________________");
            return String.valueOf(result);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException("Invalid task number!");
        }

    }
}