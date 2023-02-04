package duke.command.taskCommands;

import duke.command.Command;
import duke.tasks.Task;
import duke.tasks.Todo;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;

public class TodoTask extends Command {
    @Override
    public void processCommand(Task[] tasksList, String input) {
        tasksList[taskCount] = new Todo(input.trim());
        printHorizontalLine();
        System.out.println("Got it. I've added this task:\n" + tasksList[taskCount].toString() + "\nNow you have " +
                (taskCount + 1) + " in the list");
        printHorizontalLine();
        taskCount++;
    }
}
