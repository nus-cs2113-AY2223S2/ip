package duke.command;

import duke.data.TaskData;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.filemanager.Storage;
import duke.task.Deadline;

import java.util.NoSuchElementException;
import java.util.Scanner;

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
     * @param taskData arrayList containing all tasks
     */
    @Override
    public void executeCommand(TaskData taskData, Storage storage) throws DukeException {
        taskData.add(newTask, storage);
        System.out.println("Got it! Added \n"
                + "[D][ ] " + newTask.getDescription() + newTask.getDueDate() + "\n"
                + "to the list.");
        System.out.println("Now you have " + taskData.size() + " task(s) in the list.");
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
            String dueBy = userInputArray[1];
            newTask = new Deadline(description, dueBy);
        } catch (IndexOutOfBoundsException outOfBounds) {
            throw new DukeException("Please input all the necessary details");
        }
    }


}
