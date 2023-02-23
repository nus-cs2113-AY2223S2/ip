package duke.command;

import duke.Duke;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * contains command to list unmark tasks
 */
public class CommandUnmark {

    public static void execute(int taskNum) {
        Duke.isFileEdited = true;
        try {
            TaskList.tasks.get(--taskNum).setStatus(false);
            if (Duke.toPrint) {
                System.out.println(Ui.LINE);
                System.out.println("\tOh, ok. Task " + (taskNum + 1) + " has been marked as \"incomplete\":");
                System.out.println("\t  " + TaskList.tasks.get(taskNum).getTaskNameAndStatus());
                System.out.println(Ui.LINE);
            }
        } catch (IndexOutOfBoundsException | NullPointerException out_unmark_b) {
            if (Duke.toPrint) {
                Ui.printInvalidNumber("unmark");
            }
        }
    }
}
