package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

import java.io.IOException;

/**
 * Represents an executable command from the user. A <code>UnmarkCommand</code> object contains the serialNumber of the
 * task that the user wants to mark as incomplete.
 */
public class UnmarkCommand extends Command {
    private int serialNumber;

    public UnmarkCommand(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * This method marks the task corresponding to <code>serialNumber</code> as incomplete. It prints a string to
     * inform the user that the task have been unmarked and saves the taskList.
     *
     * @param tasks The taskList containing all the tasks in Luke.
     * @param ui A Ui object which handles outputs to the user.
     * @param storage An object responsible for saving information in Luke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmarkTask(serialNumber);
            ui.printUnmarkTask(tasks.getTaskBySerialNumber(serialNumber));
            storage.saveTaskList(tasks);
        } catch (IOException e) {
            storage.handleSaveError();
        }
    }
}