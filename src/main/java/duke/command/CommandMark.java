package duke.command;

import duke.Duke;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * contains command to mark tasks
 */
public class CommandMark {

    public static void execute(int taskNum) {
        Duke.isFileEdited = true;
        try {
            TaskList.tasks.get(--taskNum).setStatus(true);
            if (Duke.toPrint) {
                System.out.println(Ui.LINE);
                System.out.println("\tNoted. Task " + (taskNum + 1) + " has been marked as \"complete\":");
                System.out.println("\t  " + TaskList.tasks.get(taskNum).getTaskNameAndStatus());
                System.out.println(Ui.LINE);
            }
        } catch (IndexOutOfBoundsException | NullPointerException out_mark_b) {
            if (Duke.toPrint) {
                Ui.printInvalidNumber("mark");
            }
        }
    }
}
