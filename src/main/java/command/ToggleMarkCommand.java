package command;

import exception.DukeException;
import exception.ErrorMessage;
import components.TaskList;
import components.UI;
import components.Storage;


public class ToggleMarkCommand extends Command {
    public ToggleMarkCommand(String[] commandFields) {
        super(commandFields);
    }

    /**
     * Mark or Unmark the task given the index.
     *
     * @param tasks   ArrayList of tasks.
     * @param ui      Deals with interactions with the user.
     * @param storage Deals with saving and loading tasks in the file.
     * @throws DukeException If a number is not provided or the index exceeds number of tasks in the list.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(commandFields[1]);

            if (taskNumber > tasks.tasks.size()) {
                throw new DukeException(ErrorMessage.INVALID_TASK.toString());
            }

            if (commandFields[0].equals("mark")) {
                tasks.tasks.get(taskNumber - 1).toggleMark(1);
                storage.writeToFile(tasks.tasks, storage.filePath);
                ui.taskMarked();
            } else {
                tasks.tasks.get(taskNumber - 1).toggleMark(0);
                storage.writeToFile(tasks.tasks, storage.filePath);
                ui.taskUnmarked();
            }
            ui.printTask(tasks, taskNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(ErrorMessage.INVALID_NUMBER.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ErrorMessage.INVALID_NUMBER.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
