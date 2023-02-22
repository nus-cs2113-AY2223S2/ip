package duke.command;

import duke.Duke;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * contains command to list tasks
 */
public class CommandList {
    public static void execute() {
        System.out.println(Ui.LINE);
        int count = 1;
        if (Duke.taskCount == 0) {
            System.out.println("\tYou have no pending tasks! ☺");
        } else {
            System.out.println("\tHere are your tasks:");
            for (int index = 0; index < Duke.taskCount; index++) {
                System.out.print("\t" + count + ".");
                System.out.println(TaskList.tasks.get(index));
                count++;
            }
        }
        System.out.println(Ui.LINE);
    }
}
