package app.parser;

import app.exceptions.DukeException;
import app.exceptions.IncompleteCommandException;
import app.exceptions.InvalidCommandException;
import app.tasks.Deadline;
import app.tasks.Event;
import app.tasks.Task;
import app.tasks.ToDo;

/**
 * Class to handle the parsing of commands to add a new Task to Task-list.
 */
public class AddTaskParser {
    private static Task newTask;

    /**
     * Method to deconstruct the user command to add any type of new Task and
     * make it into the relevant Task type with its relevant attributes.
     * @param commandWord The type of task to be added (first word in user input).
     * @param commandDescriptor Details about the rest of the task (user input without the first word).
     * @return A Task of the correct type with information about its relevant attributes.
     * @throws DukeException If user input is in the incorrect format.
     */
    public static Task parseCommand(String commandWord, String commandDescriptor) throws DukeException {
        if (commandDescriptor.length() == 0) {
            throw new IncompleteCommandException(commandWord);
        }
        try {
            String taskDescription;
            switch (commandWord) {
            case "todo":
                taskDescription = commandDescriptor;
                newTask = new ToDo(taskDescription, false);
                break;
            case "deadline":
                String[] parts = commandDescriptor.split("/by");
                taskDescription = parts[0].trim();
                String deadline = parts[1].trim();
                newTask = new Deadline(taskDescription, false, deadline);
                break;
            case "event":
                String[] fromParts = commandDescriptor.split("/from");
                taskDescription = fromParts[0].trim();
                String[] toParts = fromParts[1].split("/to");
                String startTime = toParts[0].trim();
                String endTime = toParts[1].trim();
                newTask = new Event(taskDescription, false, startTime, endTime);
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        }
        return newTask;
    }
}
