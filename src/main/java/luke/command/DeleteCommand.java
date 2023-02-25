package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

import java.io.IOException;

/**
 * Represents an executable command from the user. A <code>DeleteCommand</code> object contains the serial number of
 * the task to be deleted. This object deletes the task corresponding to the serial number from the TaskList when the
 * execute method is called.
 */
public class DeleteCommand extends Command {
    private int serialNumber;

    public DeleteCommand(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * This method deletes a the task corresponding to <code>serialNumber</code> from the taskList. It then saves the
     * updated taskList.
     *
     * @param tasks The taskList containing all the tasks in Luke.
     * @param ui A Ui object which handles outputs to the user.
     * @param storage An object responsible for saving information in Luke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.printDeleteTask(tasks.getTaskBySerialNumber(serialNumber));
            tasks.deleteTask(serialNumber);
            storage.saveTaskList(tasks);
        } catch (IOException e) {
            storage.handleSaveError();
        }
    }
}
