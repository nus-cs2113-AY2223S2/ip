package duke.command.actionCommands;

import duke.command.Command;
import duke.exception.InvalidTaskException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;

public class DeleteTask extends Command {
    @Override
    public void processCommand(TaskList tasksList, String input) throws InvalidTaskException {
        int taskIndex = Integer.parseInt(input);
        if (taskIndex > taskCount || taskIndex < 1) {
            throw new InvalidTaskException();
        }

        String info = tasksList.get(taskIndex - 1).toString();
        tasksList.deleteTask(taskIndex - 1, info);
    }
}
