package duke.command.taskCommands;

import duke.command.Command;
import duke.exception.InvalidTaskException;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;

public class EventTask extends Command {
    @Override
    public void processCommand(TaskList tasksList, String input) throws InvalidTaskException {
        //ex: event meeting /from 2pm /to 4pm
        String[] task = input.split("/(from|to)", 3);
        if (task[1].trim().isEmpty() || task[2].trim().isEmpty()) {
            throw new InvalidTaskException();
        }
        tasksList.addTask(new Event(task[0].trim(), task[1].trim(), task[2].trim()));
        taskCount++;
    }
}
