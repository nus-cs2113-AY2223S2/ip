package duke.command.taskCommands;

import duke.command.Command;
import duke.tasks.TaskList;
import duke.tasks.Todo;

import static duke.main.Duke.taskCount;


public class TodoTask extends Command {
    @Override
    public void processCommand(TaskList tasksList, String input) {
        tasksList.addTask(new Todo(input.trim()));
        taskCount++;
    }
}
