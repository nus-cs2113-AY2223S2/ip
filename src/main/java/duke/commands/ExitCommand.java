package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command for exiting the program.
 */
public class ExitCommand extends Command{
    /**
     * Displays the message when user inputs the exit command.
     *
     * @param taskList The task list that the command is executed on.
     */
    @Override
    public void execute(TaskList taskList){
        Ui.printExit();
    }

    /**
     * Sets isRunning to be false to terminate the program.
     *
     * @return True to allow the program to stop running.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
