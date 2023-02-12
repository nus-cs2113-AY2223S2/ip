package duke.command.taskCommands;

import duke.command.Command;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;

public class TodoTask extends Command {
    @Override
    public void processCommand(ArrayList<Task> tasksList, String input) {
        tasksList.add(new Todo(input.trim()));
        printHorizontalLine();
        System.out.println("Got it. I've added this task:\n" + tasksList.get(taskCount).toString() + "\nNow you have " +
                (taskCount + 1) + " in the list");
        printHorizontalLine();
        taskCount++;
    }
}
