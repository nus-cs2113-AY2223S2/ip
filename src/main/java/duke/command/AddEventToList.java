package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.filemanager.Storage;
import duke.task.Event;
import duke.ui.Ui;

import java.util.NoSuchElementException;

public class AddEventToList extends Command {
    private Event newTask;

    /**
     * pre-processes the raw user input into array
     *
     * @param userInput raw user input string
     * @throws DukeException occurs when invalid input is found i.e missing /from
     */
    public AddEventToList(String userInput) throws DukeException {
        try {
            final String[] userInputArray = userInput.trim().split("/from|/to");
            setTasks(userInputArray);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidInputException();
        }
    }

    /**
     * Adds a new event to the list
     *
     * @param tasks arrayList containing all tasks
     */
    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.add(newTask, storage);
        ui.printTaskEvent(newTask, tasks);
    }

    /**
     * Takes the processed userInput and creates a new event object
     *
     * @param userInputArray processed userInput
     * @throws DukeException occurs when an invalid input is found
     */
    public void setTasks(String[] userInputArray) throws DukeException {
        try {
            String description = userInputArray[0];
            String startTime = userInputArray[1];
            String endTime = userInputArray[2];
            this.newTask = new Event(description, startTime, endTime);
        } catch (IndexOutOfBoundsException | NoSuchElementException ex) {
            throw new InvalidInputException();
        }
    }

}
