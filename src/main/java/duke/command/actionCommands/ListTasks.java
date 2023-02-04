package duke.command.actionCommands;

import duke.command.Command;
import duke.tasks.Task;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;

public class ListTasks extends Command {
    @Override
    public void processCommand(Task[] tasksList, String input) {
        int idxCount = 1;
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(idxCount + "." + tasksList[i].toString());
            idxCount++;
        }
        printHorizontalLine();
    }
}
