package duke.keycommands;

import duke.common.Common;
import duke.tasktypes.Task;

/**
 * Represents the command to list all the current tasks in the task list.
 */
public class ListCommand {

    /**
     * Constructs a new ListCommand object and list all the current tasks in the task list.
     */
    public ListCommand() {
        printItems();
    }

    private static void printItems() {
        for (int i = 0; i < Common.tasks.size(); ++i) {
            Task item = Common.tasks.get(i);
            if (item == null) {
                break;
            } else {
                System.out.println((i + 1) + "." + item.formatTaskToPrint());
            }
        }
    }

}
