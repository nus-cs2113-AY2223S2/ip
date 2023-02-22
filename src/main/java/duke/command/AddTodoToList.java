package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.filemanager.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.util.NoSuchElementException;

/**
 * Handles command to add a new todo to list
 */
public class AddTodoToList extends Command {
    private Task newTask;

    /**
     * create a new todo object
     *
     * @param userInput data containing todo description
     * @throws DukeException occurs when an invalid input is found
     *                       i.e missing description
     */
    public AddTodoToList(String userInput) throws DukeException {
        try {
            this.newTask = new Task(userInput.replaceFirst(" ", ""));
        } catch (IndexOutOfBoundsException | NoSuchElementException ex) {
            throw new InvalidInputException();
        }
    }

    /**
     * Adds new todo object into the arrayList
     *
     * @param tasks containing all tasks
     */
    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.add(newTask, storage);
        ui.printTaskTodo(newTask, tasks);
    }

}
