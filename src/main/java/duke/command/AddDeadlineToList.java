package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.filemanager.Storage;
import duke.task.Deadline;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AddDeadlineToList extends Command {
    private Deadline newTask;

    /**
     * preprocess userInput to create new deadline object
     *
     * @param userInput raw string containing deadline details
     * @throws DukeException occurs when input is invalid i.e /by not found
     */
    public AddDeadlineToList(String userInput) throws DukeException {
        try {
            Scanner scanner = new Scanner(userInput);
            String[] userInputArray = scanner.nextLine().trim().split("/by");
            setTasks(userInputArray);
        } catch (IndexOutOfBoundsException | NoSuchElementException ex) {
            throw new InvalidInputException();
        }
    }

    /**
     * Adds the new deadline into the arrayList
     *
     * @param tasks arrayList containing all tasks
     */
    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.add(newTask, storage);
        ui.printTaskDeadline(newTask, tasks);
    }

    /**
     * Creates a new deadline object with the processed userInput
     *
     * @param userInputArray userInput classified to the deadline parameters
     * @throws DukeException occurs when input is partial or invalid
     */
    public void setTasks(String[] userInputArray) throws DukeException {
        try {
            String description = userInputArray[0];
            String dueBy = userInputArray[1].replaceFirst(" ", "");
            try {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                newTask = new Deadline(description, dueBy, LocalDateTime.parse(dueBy, dateTimeFormatter));
            } catch (DateTimeParseException ex) {
                System.out.println("Not a date and time, you can add a datetime with dd-MM-yyyy HHmm format");
                newTask = new Deadline(description, dueBy);
            }


        } catch (IndexOutOfBoundsException outOfBounds) {
            throw new DukeException("Please input all the necessary details");
        }
    }


}
