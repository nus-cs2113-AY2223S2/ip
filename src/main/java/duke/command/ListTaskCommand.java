package duke.command;

import duke.task.Task;
import duke.util.TaskList;

/**
 * A <code>ListCommand</code> object takes care of the listing of all tasks recorded within Duke.
 */
public class ListTaskCommand extends Command {


    /**
     * Lists all task recorded in the given list.
     *
     */
    @Override
    public void run(TaskList taskList) {
            System.out.println("Here are the tasks in your list:");
            int itemNumber = 1;
            for (Task item : taskList.lists) {
                if ((itemNumber - 1) == taskList.lists.size()) {
                    break;
                }
                item.printTask(itemNumber);
                itemNumber++;
        }
    }
}
