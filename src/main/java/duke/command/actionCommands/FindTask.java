package duke.command.actionCommands;

import duke.command.Command;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

import static duke.main.Duke.printHorizontalLine;

public class FindTask extends Command {
    public void processCommand(TaskList tasks, String taskToFind) {
        String taskName;
        ArrayList<Task> tasksFound = new ArrayList<Task>();
        for (Task task : tasks) {
            taskName = task.getTaskName();
            if (taskName.equals(taskToFind)) {
                tasksFound.add(task);
            }
        }
        if (!tasksFound.isEmpty()) {
            printHorizontalLine();
            System.out.println("Here are the matching tasks in your list:");
            int idx = 1;
            for (Task task : tasksFound) {
                System.out.print(idx + ". ");
                System.out.println(task.toString());
                idx++;
            }
            printHorizontalLine();
        } else {
            printHorizontalLine();
            System.out.println("No matching tasks in your list:");
            printHorizontalLine();
        }
    }
}
