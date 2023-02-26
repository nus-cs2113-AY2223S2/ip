package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Updates the status of a task in the list.
 */
public class CommandMarking extends Command {
    private final boolean isMark;
    private final int taskNum;

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
