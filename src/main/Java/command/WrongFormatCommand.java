package command;

import jonathan.Storage;
import task.TaskList;
import jonathan.Ui;

/**
 * model a class to handle the wrong format command. inherit from Command class.
 */
public class WrongFormatCommand extends Command {
    /**
     * Method to execute the wrong format command class.
     * @param tasks list of tasks.
     * @param ui the interface of the program.
     * @param storage the storage of the program.
     * @return list of tasks.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showWrongFormat();
        return tasks;
    }
}
