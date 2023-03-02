package command;

import exception.DukeException;
import exception.ErrorMessage;
import task.Task;
import components.TaskList;
import components.UI;
import components.Storage;


public class DeleteCommand extends Command {
    public DeleteCommand(String[] commandFields) {
        super(commandFields);
    }

    /**
     * Remove an item from the ArrayList of tasks given the task index.
     *
     * @param tasks   ArrayList of tasks.
     * @param ui      Deals with interactions with the user.
     * @param storage Deals with saving and loading tasks in the file.
     * @throws DukeException If the index to delete exceeds number of tasks.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
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
