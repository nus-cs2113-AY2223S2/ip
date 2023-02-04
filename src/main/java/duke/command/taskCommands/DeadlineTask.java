package duke.command.taskCommands;

import duke.command.Command;
import duke.exception.InvalidTaskException;
import duke.tasks.Deadline;
import duke.tasks.Task;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;

public class DeadlineTask extends Command {
    @Override
    public void processCommand(Task[] tasksList, String input) throws InvalidTaskException {
        //ex: deadline return book /by 2pm
        String[] task = input.split("\\/(by)", 2);
        if (task[1].trim().isEmpty()) {
            throw new InvalidTaskException();
        }
        tasksList[taskCount] = new Deadline(task[0].trim(), task[1].trim());
        printHorizontalLine();
        System.out.println("Got it. I've added this task:\n" + tasksList[taskCount].toString() + "\nNow you have " +
                (taskCount + 1) + " in the list");
        printHorizontalLine();
        taskCount++;
    }
}
