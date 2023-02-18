package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int serialNumber;

    public UnmarkCommand(int serialNumber) {
        this.serialNumber = serialNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmarkTask(serialNumber);
            ui.printUnmarkTask(tasks.getTaskBySerialNumber(serialNumber));
            tasks.serializeTaskOrganizer();
        } catch (IOException e) {
            storage.handleSaveError();
        }
    }
}