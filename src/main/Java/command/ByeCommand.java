package command;

import jonathan.Storage;
import task.TaskList;
import jonathan.Ui;

/**
 * model a class to handle the bye command. inherit from Command class.
 */
public class ByeCommand extends Command {
    /**
     * Method to execute the bye command class.
     * @param tasks list of tasks.
     * @param ui the interface of the program.
     * @param storage the storage of the program.
     * @return list of tasks.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.showGoodbye();
        return tasks;
    }

    /**
     * Method to indicate to exit the program after executed
     * @return boolean.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
