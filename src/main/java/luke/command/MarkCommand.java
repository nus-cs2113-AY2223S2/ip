package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.InvalidIndexException;

import java.io.IOException;

/**
 * Represents an executable command from the user. A <code>MarkCommand</code> object contains the serialNumber of the
 * task that the user wants to mark as complete.
 */
public class MarkCommand extends Command {
    private int serialNumber;

    public MarkCommand(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * This method marks the task corresponding to <code>serialNumber</code> as completed. It prints a string to inform
     * the user that the task have been marked and saves the taskList.
     *
     * @param tasks The taskList containing all the tasks in Luke.
     * @param ui A Ui object which handles outputs to the user.
     * @param storage An object responsible for saving information in Luke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTask(serialNumber);
            ui.printMarkTask(tasks.getTaskBySerialNumber(serialNumber));
            storage.saveTaskList(tasks);
        } catch (IOException e) {
            storage.handleSaveError();
        }
    }
}
