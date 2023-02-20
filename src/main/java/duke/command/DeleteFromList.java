package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.filemanager.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.util.NoSuchElementException;

public class DeleteFromList extends Command {
    private int taskIndex;

    public DeleteFromList(String userInput) throws DukeException {
        try {
            String taskIndex = userInput.replace(" ", "");
            setDeleteTask(Integer.parseInt(taskIndex) - 1);
        } catch (NoSuchElementException | NumberFormatException ex) {
            throw new InvalidInputException();
        }
    }

    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            Task task = tasks.deleteTask(taskIndex, storage);
            ui.printDeletedTask(task, tasks);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Enter a number within range of list!");
        }
    }

    public void setDeleteTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }
}
