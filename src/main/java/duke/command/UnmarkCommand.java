package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.filemanager.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(String userInput) throws DukeException {
        try {
            String taskIndex = userInput.replace(" ", "");
            setUnmarkTask(Integer.parseInt(taskIndex) - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            throw new InvalidInputException();
        }
    }

    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            Task task = tasks.markAsUndone(taskIndex, storage);
            ui.printUnmarkedTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number within list size!");
        }
    }

    public void setUnmarkTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }


}
