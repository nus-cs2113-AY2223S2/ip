package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.InvalidIndexException;

import java.io.IOException;

public class MarkCommand extends Command {
    private int serialNumber;

    public MarkCommand(int serialNumber) {
        this.serialNumber = serialNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTask(serialNumber);
            ui.printMarkTask(tasks.getTaskBySerialNumber(serialNumber));
            tasks.serializeTaskOrganizer();
        } catch (IOException e) {
            storage.handleSaveError();
        }
    }
}
