package command;

import exception.DukeException;
import task.Task;

public class MarkCommand extends Command {
    public MarkCommand(String[] commands) {
        super(commands);
    }

    @Override
    public void doCommand(int taskCount, Task[] tasks) throws DukeException {
        try {
            int taskNum = Integer.parseInt(getCommands()[1]);
            tasks[taskNum - 1].markDone();
            System.out.println("____________________________________________________________" + "\nNice! I've marked this task as done:\n[" + tasks[taskNum - 1].getTaskType() + "][X] " + tasks[taskNum - 1].getDescription() + "\n" + "____________________________________________________________");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException("Invalid task number!");
        }
    }
}