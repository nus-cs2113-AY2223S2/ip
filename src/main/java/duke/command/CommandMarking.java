package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;

public class CommandMarking extends Command {
    private final int taskNum;
    private final boolean isMark;

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
