package command;

import exception.DukeException;
import exception.ErrorMessage;
import task.Task;
import components.TaskList;
import components.Ui;
import components.Storage;


public class DeleteCommand extends Command {
    public DeleteCommand(String[] commandFields) {
        super(commandFields);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int taskNumber = Integer.parseInt(commandFields[0]);
        if (taskNumber > tasks.tasks.size()) {
            throw new DukeException(ErrorMessage.INVALID_DELETE.toString());
        }

        Task taskToRemove = tasks.tasks.get(taskNumber - 1);
        tasks.tasks.remove(taskNumber - 1);
        ui.taskRemoved(tasks.tasks, taskToRemove);
        storage.writeToFile(tasks.tasks, storage.filePath);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
