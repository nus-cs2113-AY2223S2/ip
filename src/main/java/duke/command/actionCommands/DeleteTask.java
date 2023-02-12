package duke.command.actionCommands;

import duke.command.Command;
import duke.exception.InvalidTaskException;
import duke.tasks.Task;

import java.util.ArrayList;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;

public class DeleteTask extends Command {
    @Override
    public void processCommand(ArrayList<Task> tasksList, String input) throws InvalidTaskException {
        int taskIndex = Integer.parseInt(input);
        if (taskIndex > taskCount || taskIndex < 1) {
            throw new InvalidTaskException();
        }
        String info = tasksList.get(taskIndex - 1).toString();
        tasksList.remove(taskIndex - 1);
        printHorizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(info);
        taskCount--;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printHorizontalLine();

    }
}
