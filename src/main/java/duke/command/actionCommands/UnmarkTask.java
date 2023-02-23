package duke.command.actionCommands;

import duke.command.Command;
import duke.exception.InvalidTaskException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;

public class UnmarkTask extends Command {
    @Override
    public void processCommand(TaskList tasksList, String line) throws InvalidTaskException {
        int taskIndex = Integer.parseInt(line);
        if (taskIndex > taskCount || taskIndex < 1) {
            throw new InvalidTaskException();
        }

        tasksList.get(taskIndex - 1).markAsNotDone();
        printHorizontalLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasksList.get(taskIndex - 1).toString());
        printHorizontalLine();

    }

}
