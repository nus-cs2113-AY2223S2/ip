package duke.command.taskCommands;

import duke.command.Command;
import duke.exception.InvalidTaskException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;

public class DeadlineTask extends Command {
    @Override
    public void processCommand(TaskList tasksList, String input) throws InvalidTaskException {
        //ex: deadline return book /by 2pm
        String[] task = input.split("\\/(by)", 2);
        if (task[1].trim().isEmpty()) {
            throw new InvalidTaskException();
        }
        tasksList.addTask(new Deadline(task[0].trim(), task[1].trim()));
        taskCount++;
    }
}
