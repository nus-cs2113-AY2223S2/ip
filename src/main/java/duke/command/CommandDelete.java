package duke.command;

import duke.Duke;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * contains command function to delete tasks when user enters "delete (x) task names
 * lists the tasks remaining after deletion
 */
public class CommandDelete {

    public static void execute(int taskNum) {
        Duke.isFileEdited = true;
        System.out.println(Ui.LINE);
        System.out.println("\t  " + TaskList.tasks.get(taskNum - 1));
        System.out.println("\t" + "Task removed!");
        System.out.println("\t" + "Now you have tasks which are " + (Duke.taskCount - 1) + " pending tasks.");
        TaskList.tasks.remove(taskNum - 1);
        System.out.println(Ui.LINE);
        Duke.taskCount--;
    }
}
