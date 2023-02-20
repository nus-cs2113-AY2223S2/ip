package duke.command;

import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

public class ListTasksCommand extends Command {
    @Override
    public void run(TaskList taskList) {
        if (taskList.size() == 0) {
            Ui.print(Messages.EMPTY_LIST.MESSAGE);
        } else {
            Ui.print(Messages.LIST_TASKS.MESSAGE, taskList.toString());
        }
    }
}
