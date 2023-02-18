package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.InvalidIndexException;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int serialNumber;

    public DeleteCommand(int serialNumber) {
        this.serialNumber = serialNumber;
    }

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
