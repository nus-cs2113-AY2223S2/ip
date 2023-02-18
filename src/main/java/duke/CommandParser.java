package duke;

import error.*;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class contains the methods responsible for managing the different types of inputs to Duke.
 */
public class CommandParser {


    /**
     * This method manages the input entered by the user.
     *
     * @param userInput The entire String entered by the user.
     * @param command   The first word of the userInput.
     * @param tasks     The ArrayList of Tasks contained in tasks.txt.
     * @throws DukeIllegalCharacterException If userInput contains `|`.
     * @throws DukeTaskDoesNotExistException If the specified Task does not exist.
     * @throws DukeAlreadyMarkedException    If the task is already marked or unmarked.
     * @throws DukeIllegalSyntaxException    If the userInput does not conform to the required syntax.
     * @throws DukeIllegalCommandException   If the userInput contains an illegal command.
     * @throws IOException                   If tasks.txt cannot be read or written to.
     */
    public void manageInput(String userInput, String command, ArrayList<Task> tasks)
            throws DukeIllegalCharacterException, DukeTaskDoesNotExistException,
            DukeAlreadyMarkedException, DukeIllegalSyntaxException,
            DukeIllegalCommandException, IOException {

        PrintOperations.horizontalLine();

        if (userInput.contains("|")) {
            throw new DukeIllegalCharacterException();
        }

        String[] splitInput = userInput.split("\\s+");
        String firstWordOfUserInput = splitInput[0];

        switch (firstWordOfUserInput) {
        case "list":
            PrintOperations.list(tasks);
            break;
        case "mark":
        case "unmark":
        case "delete":
            handleMarkAndDelete(userInput, command, tasks);
            break;
        case "find":
            handleFind(userInput, tasks);
            break;
        default:
            addNewTask(userInput, command, tasks);
        }

        // Save task ArrayList information into tasks.txt
        FileOperations.saveArrayListToFile(tasks);
    }

    private void handleMarkAndDelete(String userInput, String command, ArrayList<Task> tasks)
            throws DukeTaskDoesNotExistException, DukeAlreadyMarkedException {

        int taskIndex = Integer.parseInt(userInput.substring(userInput.length() - 1)) - 1;

        // If task index does not exist, throw exception
        if ((taskIndex + 1) > tasks.size() || taskIndex < 0) {
            throw new DukeTaskDoesNotExistException();
        }

        // Mark as done
        switch (command) {
        case "mark":
            tasks.get(taskIndex).markAsDone();
            break;
        case "unmark":
            tasks.get(taskIndex).markAsNotDone();
            break;
        case "delete":
            PrintOperations.taskRemoved(taskIndex, tasks);
            tasks.remove(taskIndex);
            PrintOperations.numberOfTasks(tasks);
            break;
        }
    }

    private void addNewTask(String userInput, String command, ArrayList<Task> tasks)
            throws DukeIllegalSyntaxException, DukeIllegalCommandException {

        Task newTask;

        // Depending on the type of command, the input gets parsed into the different handlers
        switch (command) {
        case "deadline":
            String[] deadlineArgs = Deadline.handler(userInput);
            newTask = new Deadline(deadlineArgs[0], deadlineArgs[1]);
            break;
        case "event":
            String[] eventArgs = Event.handler(userInput);
            newTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
            break;
        case "todo":
            String todoCommand = Todo.handler(userInput);
            newTask = new Todo(todoCommand);
            break;
        default:
            throw new DukeIllegalCommandException();
        }

        tasks.add(newTask);
        PrintOperations.addTask(newTask);
        PrintOperations.numberOfTasks(tasks);
    }


    private void handleFind(String userInput, ArrayList<Task> tasks) {
        userInput = userInput.replace("find ", "");

        // Necessary to declare userInput as a constant variable for input into streams
        final String finalUserInput = userInput;

        ArrayList<Task> filteredList = tasks.stream()
                .filter(t -> t.getDescription().contains(finalUserInput))
                .collect(Collectors.toCollection(ArrayList::new));

        PrintOperations.foundTasks();
        PrintOperations.bulletedList(filteredList);
    }
}
