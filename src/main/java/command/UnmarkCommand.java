package command;

import exception.DukeException;
import task.Task;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    public UnmarkCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public void doCommand(ArrayList<Task> tasks) throws DukeException {
        try {
            int taskNum = Integer.parseInt(getCommands().get(1));
            tasks.get(taskNum - 1).unmarkDone();
            System.out.println("____________________________________________________________"
                    + "\nOK, I've marked this task as not done yet:\n" + tasks.get(taskNum - 1).getSummary() + "\n" +
                    "____________________________________________________________");
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException("Invalid task number!");
        }
    }
}
