package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command for terminating the program.
 */
public class ExitCommand extends Command {
    /**
     * Displays the shutdown message.
     *
     * @param taskList The task list that the command is executed on.
     */
    @Override
    public void run(TaskList taskList) {
        Ui.printExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
