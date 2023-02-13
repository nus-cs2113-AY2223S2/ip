package command;

import exception.DukeException;
import task.Task;

import java.util.ArrayList;

public class MarkCommand extends Command {
    public MarkCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public void doCommand(ArrayList<Task> tasks) throws DukeException {
        try {
            int taskNum = Integer.parseInt(getCommands().get(1));
            tasks.get(taskNum - 1).markDone();
            System.out.println("____________________________________________________________"
                    + "\nNice! I've marked this task as done:\n["
                    + tasks.get(taskNum - 1).getTaskType() + "][X] "
                    + tasks.get(taskNum - 1).getDescription() + "\n"
                    + "____________________________________________________________");
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException("Invalid task number!");
        }
    }
}