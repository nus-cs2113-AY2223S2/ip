package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class ListTasksCommand extends Command {
    @Override
    public void run(TaskList taskList) {
        Ui.printTaskList(taskList);
    }
}
