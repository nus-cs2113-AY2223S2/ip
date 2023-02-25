package app.parser;

import app.exceptions.DukeException;
import app.exceptions.IncompleteCommandException;
import app.exceptions.InvalidCommandException;
import app.tasks.Deadline;
import app.tasks.Event;
import app.tasks.Task;
import app.tasks.ToDo;

public class AddTaskParser {
    private static Task newTask;

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
