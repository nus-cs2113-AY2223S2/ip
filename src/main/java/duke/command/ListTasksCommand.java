package duke.command;

import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

/**
 * Command for displaying the task list.
 */
public class ListTasksCommand extends Command {
    /**
     * Displays the task list.
     *
     * @param taskList The task list that the command is executed on.
     */
    @Override
    public void run(TaskList taskList) {
        if (taskList.size() == 0) {
            Ui.print(Messages.EMPTY_LIST.MESSAGE);
        } else {
            Ui.print(Messages.LIST_TASKS.MESSAGE, taskList.toString());
        }
    }
}
