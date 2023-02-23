package duke.command.actionCommands;

import duke.command.Command;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;

public class ListTasks extends Command {
    @Override
    public void processCommand(TaskList tasksList, String input) {
        int idxCount = 1;
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(idxCount + "." + tasksList.get(i).toString());
            idxCount++;
        }
        printHorizontalLine();
    }
}
