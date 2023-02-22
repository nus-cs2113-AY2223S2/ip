package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidScheduleException;
import duke.filemanager.Storage;
import duke.task.Event;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

/**
 * Handles the event command to add a new event to the list
 */
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
            final String[] userInputArray = userInput.trim().split(" /from | /to ");
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
            String startString = userInputArray[1];
            String endString = userInputArray[2];
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            try {
                LocalDateTime startTime = LocalDateTime.parse(startString, dateTimeFormatter);
                LocalDateTime endTime = LocalDateTime.parse(endString, dateTimeFormatter);
                if (endTime.isBefore(startTime)) {
                    throw new InvalidScheduleException();
                }
                newTask = new Event(description, startString, endString
                        , startTime
                        , endTime);
            } catch (DateTimeParseException ex) {
                System.out.println("Not a date and time, you can add a datetime with dd-MM-yyyy HHmm format");
                this.newTask = new Event(description, startString, endString);
            }
        } catch (IndexOutOfBoundsException | NoSuchElementException ex) {
            throw new InvalidInputException();
        }
    }

}
