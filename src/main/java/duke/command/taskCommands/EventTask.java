package duke.command.taskCommands;

import duke.command.Command;
import duke.exception.InvalidTaskException;
import duke.tasks.Event;
import duke.tasks.Task;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;

public class EventTask extends Command {
    @Override
    public void processCommand(Task[] tasksList, String input) throws InvalidTaskException {
        //ex: event meeting /from 2pm /to 4pm
        String[] task = input.split("/(from|to)", 3);
        if (task[1].trim().isEmpty() || task[2].trim().isEmpty()) {
            throw new InvalidTaskException();
        }
        tasksList[taskCount] = new Event(task[0].trim(), task[1].trim(), task[2].trim());
        printHorizontalLine();
        System.out.println("Got it. I've added this task:\n" + tasksList[taskCount].toString() + "\nNow you have " +
                (taskCount + 1) + " in the list");
        printHorizontalLine();
        taskCount++;
    }
}
