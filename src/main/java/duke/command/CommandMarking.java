package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * Updates the status of a task in the list.
 */
public class CommandMarking extends Command {
    private int taskNum;
    private boolean isMark;

    public CommandMarking(int taskNum, boolean isMark) {
        this.taskNum = taskNum;
        this.isMark = isMark;
    }

    @Override
    public void executor(TaskList tasks, UI ui) throws Exception {
        String result = tasks.setStatus(taskNum - 1, isMark);
        ui.print(result);
    }
}
